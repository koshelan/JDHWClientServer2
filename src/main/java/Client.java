import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 55555);
            final SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(socketAddress);
            System.out.println("Присоединились к серверу");
            final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);

            while (true) {
                System.out.println("введите сообщение");
                final String msg = scanner.nextLine();
                if (msg.equals("end")) {
                    break;
                }
                socketChannel.write(ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8)));
                int byteCount = socketChannel.read(inputBuffer);
                System.out.println(new String(inputBuffer.array(), 0, byteCount, StandardCharsets.UTF_8));
                inputBuffer.clear();
                if (msg.equals("off")) {
                    break;
                }
            }
            System.out.println("Клиент закончил работать");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
