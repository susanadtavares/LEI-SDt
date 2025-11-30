import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static final int PORT = 7896;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        System.out.println("Servidor à escuta na porta " + PORT + "...");
        try (ServerSocket listenSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = listenSocket.accept();
                new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen: " + e.getMessage());
        }
    }
}
