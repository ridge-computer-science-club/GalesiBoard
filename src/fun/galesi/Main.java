package fun.galesi;


import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

import com.sun.net.httpserver.*;

public class Main {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            printRequestInfo(t);

            String response = "This is the response";
            t.sendResponseHeaders(200, response.getBytes("UTF-8").length);
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes("UTF-8"));
            os.close();
        }

        private void printRequestInfo(HttpExchange exchange) {
            System.out.println("-- headers --");
            Headers requestHeaders = exchange.getRequestHeaders();
            requestHeaders.entrySet().forEach(System.out::println);

            System.out.println("-- principle --");
            HttpPrincipal principal = exchange.getPrincipal();
            System.out.println(principal);

            System.out.println("-- HTTP method --");
            String requestMethod = exchange.getRequestMethod();
            System.out.println(requestMethod);

            System.out.println("-- query --");
            URI requestURI = exchange.getRequestURI();
            String query = requestURI.getQuery();
            System.out.println(query);
        }
    }
}


