import java.io.*;
import java.net.*;

public class CalculatorHttpServer {
    private static final int PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Calculator HTTP Server запущен на порту " + PORT);
            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    handleClient(clientSocket);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка запуска сервера: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

        String line = in.readLine();
        if (line == null) return;

        String[] requestParts = line.split(" ");
        String method = requestParts[0];
        String path = requestParts[1];

        if (method.equals("GET") && path.startsWith("/calculate")) {
            handleCalculate(path, out);
        } else {
            handleNotFound(out);
        }

        out.flush();
    }

    private static void handleCalculate(String path, PrintWriter out) {
        String[] queryParams = path.split("\\?")[1].split("&");
        String[] aParam = queryParams[0].split("=");
        String[] bParam = queryParams[1].split("=");
        String[] opParam = queryParams[2].split("=");

        double a = Double.parseDouble(aParam[1]);
        double b = Double.parseDouble(bParam[1]);
        String op = opParam[1];

        double result;
        switch (op) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    sendHttpResponse(out, 400, "<html><body><h1>Error: Division by zero</h1></body></html>");
                    return;
                }
                result = a / b;
                break;
            default:
                sendHttpResponse(out, 400, "<html><body><h1>Error: Invalid operation</h1></body></html>");
                return;
        }

        sendHttpResponse(out, 200, "<html><body><h1>Result: " + result + "</h1></body></html>");
    }

    private static void handleNotFound(PrintWriter out) {
        sendHttpResponse(out, 404, "<html><body><h1>404 Not Found</h1></body></html>");
    }

    private static void sendHttpResponse(PrintWriter out, int statusCode, String body) {
        out.println("HTTP/1.1 " + statusCode + " OK");
        out.println("Content-Type: text/html");
        out.println("Content-Length: " + body.length());
        out.println();
        out.println(body);
    }
}
