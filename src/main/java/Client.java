import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
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
            System.out.println("Создалось соединение");
                final ByteBuffer inputBuffer = ByteBuffer.allocate(2 << 10);
                while (true) {

                    System.out.println("введите сообщение");
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



            socket = new Socket("127.0.0.1", 55555); /* выбран Blocking способ т.к. невозможно действовать
            без всех входных данных, и дальнейшая работа не возможна без возврата результата */
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            String num;
            do {
                System.out.println("Введите целое число не меньше 0");
                num = scanner.nextLine();
            } while (!isNumber(num));
            System.out.println("->" + num);
            out.println(num);
            System.out.println(num + " член последовательности фибоначи равен " + in.readLine());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
