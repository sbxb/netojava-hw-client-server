import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", Server.SERVER_PORT);
             PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            writer.println("John Smith");
            System.out.println("Server replied: " + reader.readLine());
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("ERROR Client failure: " + e.getMessage());
            System.exit(1);
        }
    }
}
