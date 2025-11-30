import java.io.*;
import java.net.Socket;

public class Connection extends Thread {
    private final Socket clientSocket;

    public Connection(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.start();
    }

    @Override
    public void run() {
        ObjectInputStream oin = null;
        DataOutputStream dout = null;
        try {
            oin = new ObjectInputStream(clientSocket.getInputStream());
            Person p = (Person) oin.readObject();
            System.out.println("[SERVER] Recebido: " + p);

            dout = new DataOutputStream(clientSocket.getOutputStream());
            dout.writeUTF(p.getPlace().getLocality());
            dout.flush();

        } catch (Exception e) {
            System.out.println("[SERVER] IO: " + e.getMessage());
        } finally {
            try { if (oin != null) oin.close(); } catch (IOException ignored) {}
            try { if (dout != null) dout.close(); } catch (IOException ignored) {}
            try { clientSocket.close(); } catch (IOException ignored) {}
        }
    }
}
