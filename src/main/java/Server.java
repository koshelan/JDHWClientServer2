import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {

    public static void main(String[] args) {
        System.out.println("Сервер начал работу");

        try {
            final ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress("localhost", 55555));
            end:
            while (true) {
                try (SocketChannel socketChannel = serverChannel.accept()) {
                    final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);
                    System.out.println("Создалось соединение");

                    while (socketChannel.isConnected()) {
                        int byteCount = socketChannel.read(inputBuffer);
                        if (byteCount == -1) {
                            break;
                        }
                        final String text = new String(inputBuffer.array(), 0, byteCount, StandardCharsets.UTF_8);
                        inputBuffer.clear();
                        System.out.println("ввод: " + text);
                        final String result = text.replace(" ", "");
                        System.out.println("вывод: " + result);
                        socketChannel.write(ByteBuffer.wrap(result.getBytes(StandardCharsets.UTF_8)));
                        if (text.equals("off")) {
                            break end;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Сервер закончил работу");
    }

}
