import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 7896;
    

        try {
            System.out.println("[CLIENT] Ligado a " + host + ":" + port);

            Socket s = new Socket(host, port);

            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            //cria e envia o objeto Pessoa
            Place place = new Place("3500-145", "Viseu");
            Person p = new Person("Patrícia", place, 2002);

            System.out.println("[CLIENT] A enviar: " + p);
            out.writeObject(p);

            DataInputStream dout = new DataInputStream(s.getInputStream());


            String resp = (String) dout.readUTF();
            System.out.println("[CLIENT] Servidor respondeu: " + resp);

        } catch (Exception e) {
            System.out.println("[CLIENT] Erro: " + e.getMessage());
        }
    }
}
