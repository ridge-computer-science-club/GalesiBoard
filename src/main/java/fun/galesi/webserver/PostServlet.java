package fun.galesi.webserver;

import org.eclipse.jetty.http.HttpStatus;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import arduino.*;


public class PostServlet extends HttpServlet
{
	private static final char STX = 0x2;
	private static final char ETX = 0x3;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException
	{

		ServletContext ctx = request.getServletContext();
		Arduino arduino = (Arduino) ctx.getAttribute("arduino");

		// not a good way to get data, should use keys and stuff.
		String data = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		data = data.toUpperCase();

		// Write your code here that uses data.

		System.out.println(data);

		arduino.openConnection();
		arduino.serialWrite(STX + data + ETX);
		arduino.closeConnection();

		response.setContentType("text/plain");
		response.setStatus(HttpStatus.OK_200);
		response.getWriter().println("success"); // TODO: Implement different responses based on what happens on the board (i.e success or failure.)
	}
}
