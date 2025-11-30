import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        int serverPort = 7896;
        try (ServerSocket listenSocket = new ServerSocket(serverPort)) {
            System.out.println("[SERVER] A ouvir em 0.0.0.0:" + serverPort + " ...");
            while (true) {
                Socket clientSocket = listenSocket.accept(); // bloqueia à espera de ligação
                new Connection(clientSocket); // trata numa thread
            }
        } catch (IOException e) {
            System.out.println("[SERVER] Erro: " + e.getMessage());
        }
    }
}
