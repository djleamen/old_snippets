package softwaresystems.Sockets;
import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Server address
        int port = 6000; // Must match the server's port

        try (Socket socket = new Socket(serverAddress, port);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader console = new BufferedReader(new InputStreamReader(System.in))) {

            // Modify client to read user name and send it to server
            System.out.print("Enter your name: ");
            String userName = console.readLine();
            output.println(userName);

            // Server replies include the user name; until the user enters 'exit' as message
            String message;
            do {
                System.out.print("Enter message: ");
                message = console.readLine(); // Read user input
                output.println(message); // Send to server

                String response = input.readLine(); // Read server response
                System.out.println("Server replied: " + response);
            } while (!"exit".equalsIgnoreCase(message));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}