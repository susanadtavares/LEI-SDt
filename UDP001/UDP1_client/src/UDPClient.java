import java.net.*;
import java.io.*;

public class UDPClient{
    public static void main(String args[]){
        DatagramSocket aSocket = null;

        try {
            aSocket = new DatagramSocket();
            int n_msg = 0;
            while(n_msg < 10) {

                int n = n_msg + 1;
                String mensagem = n + ",vou enviar esta mensagem ao servidor";
                byte[] m = mensagem.getBytes();

                InetAddress aHost = InetAddress.getByName("127.0.0.1");
                int serverPort = 6789;

                DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);

                aSocket.send(request);

                byte[] buffer = new byte[1000];
                n_msg++;

                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);

                aSocket.receive(reply);

                System.out.println("Reply: " + new String(reply.getData(), 0, reply.getLength(), java.nio.charset.StandardCharsets.UTF_8));
            }

        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e){System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();}
    }
}