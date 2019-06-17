package fun.galesi.webserver;

import arduino.Arduino;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;


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


		String data = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

		try
		{
			JSONObject obj = new JSONObject(data);
			String input = obj.getString("input");

			/*JSONArray arr = obj.getJSONArray("posts");
			for (int i = 0; i < arr.length(); i++)
			{
				String post_id = arr.getJSONObject(i).getString("post_id");
			}*/

			if (input != null)
			{
				input = input.toUpperCase();

				System.out.println(input);

				try {
					arduino.openConnection();
					arduino.serialWrite(STX + input + ETX);
					arduino.closeConnection();
				} catch (NullPointerException e)
				{
					System.err.println("Arduino not found on serial port!");
				}

			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}


		response.setContentType("text/plain");
		response.setStatus(HttpStatus.OK_200);
		response.getWriter().println("success"); // TODO: Implement different responses based on what happens on the board (i.e success or failure.)
	}
}
