import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class UDPServer {

    private static final Map<SocketAddress, Integer> lastInOrderByClient = new HashMap<>();

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(6789);
            System.out.println("[SERVER] A ouvir em 0.0.0.0:6789 (UDP)...");
            byte[] buffer = new byte[2048];

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                socket.receive(request);

                String msg = new String(request.getData(), 0, request.getLength(), StandardCharsets.UTF_8);
                SocketAddress clientKey = new InetSocketAddress(request.getAddress(), request.getPort());
                String from = request.getAddress().getHostAddress() + ":" + request.getPort();
                System.out.println("[SERVER] Recebido de " + from + " -> \"" + msg + "\" (" + request.getLength() + " bytes)");

                int lastInOrder = lastInOrderByClient.getOrDefault(clientKey, 0);
                int expected = lastInOrder + 1;

                int commaIdx = msg.indexOf(',');
                if (commaIdx <= 0) {
                    sendWaitingFor(socket, request, expected);
                    System.out.println("[SERVER] Formato inválido. Esperado N antes da vírgula. Enviado waitingfor," + expected);
                    continue;
                }

                String nStr = msg.substring(0, commaIdx).trim();
                int n;
                try {
                    n = Integer.parseInt(nStr);
                } catch (NumberFormatException e) {
                    sendWaitingFor(socket, request, expected);
                    System.out.println("[SERVER] N inválido \"" + nStr + "\". Enviado waitingfor," + expected);
                    continue;
                }

                if (n == expected) {
                    lastInOrderByClient.put(clientKey, n);

                    String payload = msg.substring(commaIdx + 1);
                    byte[] sendData = payload.getBytes(StandardCharsets.UTF_8);
                    DatagramPacket reply = new DatagramPacket(sendData, sendData.length, request.getAddress(), request.getPort());
                    socket.send(reply);

                    System.out.println("[SERVER] Em ordem (N=" + n + "). L atualizado para " + n + ". Enviado eco do payload.");
                } else {
                    sendWaitingFor(socket, request, expected);
                    System.out.println("[SERVER] Fora de ordem (N=" + n + ", esperado=" + expected + "). Enviado waitingfor," + expected);
                }
            }

        } catch (SocketException e) {
            System.out.println("[SERVER][Socket] " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[SERVER][IO] " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (socket != null) socket.close();
        }
    }

    private static void sendWaitingFor(DatagramSocket socket, DatagramPacket request, int expected) throws IOException {
        String resp = "waitingfor," + expected;
        byte[] sendData = resp.getBytes(StandardCharsets.UTF_8);
        DatagramPacket reply = new DatagramPacket(sendData, sendData.length, request.getAddress(), request.getPort());
        socket.send(reply);
    }
}