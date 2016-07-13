
package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Hello World Servlet expose get method to say hello to input user
 */
public class HelloWorldServlet extends HttpServlet {

    /**
     * This method is used to map a GET request from the client side
     *
     * @param request  the HttpServletRequest instance
     * @param response the HttpServletResponse instance
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String user = "";
//
//        // Set the response type
//        response.setContentType("text/html");
//
//        // Obtain the user from the request instance
//        user = request.getParameter("user");
//        //Write the response content
//        response.getWriter().write("Hello <b>" + user + "</b>");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/helloforward");
        request.setAttribute("testAttribute", "Enjoy Z2H");
        requestDispatcher.forward(request, response);

    }

}