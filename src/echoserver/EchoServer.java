package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        ServerSocket sock;

        try {
            sock = new ServerSocket(portNumber);
            while (true) {

                Socket client = sock.accept();
                InputStream input = client.getInputStream();
                OutputStream output = client.getOutputStream();
                int data = input.read();
                while (data >= 0) {
                    output.write(data);
                    data = input.read();
                }
                output.flush();
                client.close();
                sock.close();
            }
            // *Very* minimal error handling.
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}
