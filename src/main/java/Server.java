import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int SERVER_PORT = 8080;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started successfully");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    final String name = in.readLine();
                    System.out.println("New connection accepted. Got '" + name + "' from port " + clientSocket.getPort());
                    out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
                } catch (IOException e) {
                    System.out.println("ERROR Server failed to process client connection: " + e.getMessage());
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("ERROR Server failure: " + e.getMessage());
            System.exit(1);
        }
    }
}
