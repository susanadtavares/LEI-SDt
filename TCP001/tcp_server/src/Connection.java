import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Connection extends Thread {
    private final Socket clientSocket;
    private ObjectInputStream in;   // para ler o objeto Person
    private DataOutputStream out;   // para escrever a resposta em UTF

    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            // ordem: abrir out (data) e depois in (object)
            out = new DataOutputStream(clientSocket.getOutputStream());
            in  = new ObjectInputStream(clientSocket.getInputStream());
            this.start();
        } catch (IOException e) {
            System.out.println("Connection: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
            Object obj = in.readObject();   // recebe o Person
            String reply;
            if (obj instanceof Person) {
                Person p = (Person) obj;
                System.out.println("Recebi: " + p);
                reply = p.getName();
            } else {
                reply = "Objeto inválido";
            }

            out.writeUTF(reply);            // devolve em UTF
            out.flush();

        } catch (EOFException e) {
            System.out.println("EOF: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try { clientSocket.close(); } catch (IOException ignored) {}
        }
    }
}
