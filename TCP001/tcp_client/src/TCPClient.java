import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 7896;

        try (Socket s = new Socket(host, port)) {
            // envia objeto
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.flush();
            Person p = new Person("Susana", 2004);
            System.out.println("A enviar: " + p);
            oos.writeObject(p);
            oos.flush();

            // lê resposta como UTF
            DataInputStream in = new DataInputStream(s.getInputStream());
            String reply = in.readUTF();
            System.out.println("Resposta do servidor: " + reply);

        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }
}
