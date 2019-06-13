package fun.galesi.webserver;

import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class PostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // not a good way to get data, should use keys and stuff.
        String data = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

        // Write your code here that uses data.

        System.out.println(data);

        response.setContentType("text/plain");
        response.setStatus(HttpStatus.OK_200);
        response.getWriter().println("success"); // TODO: Implement different responses based on what happens on the board (i.e success or failure.)
    }
}
