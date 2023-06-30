import java.io.*;
import java.net.*;

class Server {

    // 2 variables to make for server and client
    ServerSocket server;
    Socket socket;

    // 2 variables to read and write
    BufferedReader br;
    PrintWriter out;

    // Constructor ...
    public Server() {
        try {
            server = new ServerSocket(7777);
            System.out.println("Server is ready to accept connection ");
            System.out.println("Waiting ..... ");

            // Client ka request accept karlo
            socket = server.accept();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream());

            startReading();
            startWriting();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startReading() {
        // Thread read karke deta rahega

        Runnable r1 = () -> {
            System.out.println("Reader started ");
            try {
                while (true) {

                    String msg = br.readLine();

                    if (msg.equals("exit")) {
                        System.out.println("Client terminated the chat .... ");

                        socket.close();

                        break;
                    }
                    System.out.println("client: " + msg);

                }
            } catch (Exception e) {
                // e.printStackTrace();
                System.out.println("Connection is Closed... ");
            }
        };

        new Thread(r1).start();
    }

    public void startWriting() {
        // Thread - data se lega and send karega client tak
        Runnable r2 = () -> {
            System.out.println("Writer started.... ");
            try{
            while (!socket.isClosed()) {

                BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

                String content = br1.readLine();

                

                out.println(content);
                out.flush();

                if (content.equals("exit")) {
                    socket.close();
                    break;
                }

            }}catch(Exception e){
                // e.printStackTrace();
                System.out.println("Connection Closed... ");
            }
            // System.out.println("Connection Closed... ");
        };

        new Thread(r2).start();
    }

    public static void main(String[] args) {
        System.out.println("This is server");
        new Server();
    }
}