package ro.teamnet.zth.web;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.fmk.AnnotationScanUtils;
import ro.teamnet.zth.fmk.MethodAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by user on 7/14/2016.
 */
public class MyDispatcherServlet extends HttpServlet {

    //rol de registru
    //key :urlPath
    // valoare: informatii despre metoda care va procesa acest url
    HashMap<String, MethodAttributes> allowMethods =
            new HashMap<>();

    @Override
    public void init() throws ServletException {
        //17
        try {
            //cautare clase din pachet
            Iterable<Class> controllers = AnnotationScanUtils.getClasses("ro.teamnet.zth.appl.controller");
            for (Class controller : controllers) {
                if (controller.isAnnotationPresent(MyController.class)) {
                    //luam adnotarea
                    MyController myCtrlAnnotation = (MyController) controller.getAnnotation(MyController.class);
                    String controllerUrlPath = myCtrlAnnotation.urlPath();//o parte din cheie-radacina
                    //luam metodele dintr-o clasa fol controller.getmethods
                    Method[] controllerMethods = controller.getMethods();
                    for (Method controllerMethod : controllerMethods) {
                        //pt fiecare metoda verificam daca este adnotarea prezenta
                        if (controllerMethod.isAnnotationPresent(MyRequestMethod.class)) {
                            MyRequestMethod myRequestMethod =
                                    controllerMethod.getAnnotation(MyRequestMethod.class);
                            //cheia e urlPath
                            String methodUrlPath = myRequestMethod.urlPath();
                            //consturiesc cheia pt hashmap prin concatenarea controllerpath
                            //si methodUrlPath

                            String urlPath = controllerUrlPath + methodUrlPath;
                            //valoare e un obiect de tip methodsAtribute
                            //construiesc valoare
                            MethodAttributes methodsAttribute = new MethodAttributes();
                            methodsAttribute.setControllerClass(controller.getName());
                            methodsAttribute.setMethodType(myRequestMethod.methodType());
                            methodsAttribute.setMethodName(controllerMethod.getName());
                            //adaug cheia si valoarea
                            allowMethods.put(urlPath, methodsAttribute);

                        }

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

        //instructiuni de delegare
        dispatchReply("GET", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatchReply("POST", req, resp);
        //instructiuni de delegare
    }

    private void dispatchReply(String get, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object r = null;
        try {
            r = dispatch(req, resp);
        } catch (Exception ex) {
            sendExceptionError(ex, req, resp);
        }
        try {
            reply(r, req, resp);
        } catch (IOException e) {
            sendExceptionError(e, req, resp);
            e.printStackTrace();
        }
    }

    private void sendExceptionError(Exception ex, HttpServletRequest req, HttpServletResponse resp) {
    }

    private void reply(Object r, HttpServletRequest req, HttpServletResponse resp) throws IOException {

        PrintWriter out = resp.getWriter();
        out.printf(r.toString());
    }

    private Object dispatch(HttpServletRequest req, HttpServletResponse resp) {
        //22
        String path = req.getPathInfo();
        MethodAttributes methodAttributes = allowMethods.get(path);
        if (methodAttributes == null) {
            //nu putem procesa url-ul
            return "Hello";
        }


        String controllerName = methodAttributes.getControllerClass();
        try {
            Class<?> controllerClass = Class.forName(controllerName);
            Object controllerInstance = controllerClass.newInstance();
            Method method = controllerClass.getMethod(methodAttributes.getMethodName());
            Object result = method.invoke(controllerInstance);

            return result;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


        return "Hello";
    }


}