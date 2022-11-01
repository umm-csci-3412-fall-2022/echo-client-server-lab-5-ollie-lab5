package echoserver;

import java.net.*;
import java.io.*;


public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) {
        String server;
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }
        try {
            Socket socket = new Socket(server, portNumber);
            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            int data = System.in.read();
            while (data >= 0) {
                output.write(data);
                System.out.write(input.read());
                data = System.in.read();
            }
            output.flush();
            System.out.flush();
            socket.close();

        // Provide some minimal error handling.
        } catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}
