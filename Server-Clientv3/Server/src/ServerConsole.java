import java.io.*;
//import ocsf.server.*;
import common.*;
import server.*;

public class ServerConsole implements ChatIF {

    final public static int DEFAULT_PORT = 5555;

    EchoServer server;

    public ServerConsole(int port) throws IOException {
        server = new EchoServer(port, this);
    }

    @Override
    public void display(String message) {
        System.out.println(message);

    }

    public void accept() {
        try {
            BufferedReader fromConsole = new BufferedReader(new InputStreamReader(System.in));
            String message;
            try {
                while (true) {
                    message = fromConsole.readLine();
                    server.handleMessageFromServerUI(message);
                }
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } catch (Exception ex) {
            display("ERROR!");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 0;
        try {
            port = Integer.parseInt(args[0]);
        } catch (Throwable t) {
            port = DEFAULT_PORT;
        }

        try {
            ServerConsole sv = new ServerConsole(port);
            sv.accept();
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Could not start listening for clients.");
        }
    }
}