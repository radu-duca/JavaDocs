package ro.teamnet.zth;

import org.codehaus.jackson.map.ObjectMapper;
import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.api.annotations.MyRequestParam;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * Created by user on 7/14/2016.
 */
public class MyDispatcherServlet extends HttpServlet {

    /**
     * key: urlPath
     * val: method info
     */
    Map<String, MethodAttributes> allowedMethods = new HashMap<String, MethodAttributes>();

    @Override
    public void init() throws ServletException {

        try {
            Iterable<Class> controllers = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller : controllers)
                if (controller.isAnnotationPresent(MyController.class)) {
                    MyController myControllerAnnotation =
                            (MyController) controller.getAnnotation(MyController.class);
                    String controllerUrlPath = myControllerAnnotation.urlPath();
                    Method[] methods = controller.getMethods();

                    for (Method controllerMethod : methods) {
                        if (controllerMethod.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod myRequestMethod =
                                    controllerMethod.getAnnotation(MyRequestMethod.class);
                            String methodUrlPath = myRequestMethod.urlPath();
                            // build hashmap key
                            String urlPath = controllerUrlPath + methodUrlPath + myRequestMethod.methodType();

                            // build hashmap value
                            MethodAttributes methodAttributes = new MethodAttributes();
                            methodAttributes.setControllerClass(controller.getName());
                            methodAttributes.setMethodType(myRequestMethod.methodType());
                            methodAttributes.setMethodName(controllerMethod.getName());
                            methodAttributes.setParameterTypes(controllerMethod.getParameterTypes());

                            // add to map
                            allowedMethods.put(urlPath, methodAttributes);
                        }
                    }
                }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("POST", req, resp);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("DELETE", req, resp);
    }

    protected void dispatchReply(String type, HttpServletRequest req, HttpServletResponse resp) {

        Object r = null;
        try {
            r = dispatch(type, req, resp);
            reply(r, req, resp);
        } catch (Exception e) {
            sendExceptionError(e, req, resp);
        }
    }

    private void sendExceptionError(Exception e, HttpServletRequest req, HttpServletResponse resp) {

    }

    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        resp.setContentType("text/javascript");
//        resp.getWriter().printf(r.toString());
        ObjectMapper objectMapper = new ObjectMapper();
        resp.getWriter().printf(objectMapper.writeValueAsString(r));
    }

    private Object dispatch(String typ, HttpServletRequest req, HttpServletResponse resp) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String pathInfo = req.getPathInfo() + typ;


        MethodAttributes methodAttributes = allowedMethods.get(pathInfo);
        Object controller = Class.forName(methodAttributes.getControllerClass()).newInstance();
        List<Object> params = new ArrayList<>();

        Method method = controller.getClass().getMethod(methodAttributes.getMethodName(), methodAttributes.getParameterTypes());
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            if (parameter.isAnnotationPresent(MyRequestParam.class)) {
                MyRequestParam annotation = parameter.getAnnotation(MyRequestParam.class);
                String name = annotation.name();
                String requestParamValue = req.getParameter(name);
                Class<?> type = parameter.getType();
                Object requestParamObject = new ObjectMapper().readValue(requestParamValue, type);
                params.add(requestParamObject);
            }
        }
        return method.invoke(controller, params.toArray());
    }
}