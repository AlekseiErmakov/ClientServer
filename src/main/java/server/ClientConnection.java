package server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class ClientConnection implements Runnable {
    private Socket socket;
    private static final Logger connectionLog = LogManager.getLogger(ClientConnection.class);

    public ClientConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new PrintWriter(socket.getOutputStream()))) {
            connectionLog.info("Streams opened");
            while (true) {
                String request = in.readLine();
                out.write(request);
                out.newLine();
                out.flush();
            }
        } catch (IOException e) {
            connectionLog.error("Connection closed");
        }
    }

}
