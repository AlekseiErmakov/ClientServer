package server;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread {
    private final int PORT = 19000;
    private ServerSocket serverSocket;
    private static final Logger serverLog = LogManager.getLogger(Server.class);
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    @Override
    public void run() {
        startServer();
        listen();
    }

    private void listen() {
        while (!isInterrupted()){
            try {
                Socket socket = serverSocket.accept();
                serverLog.info("New connection by " + socket.getLocalSocketAddress());
                ClientConnection clCon = new ClientConnection(socket);
                executorService.execute(clCon);
                serverLog.info(String.format("Connection with %s started",socket.getLocalSocketAddress()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void startServer(){
        try {
            serverSocket = new ServerSocket(PORT);
            serverLog.info("server.Server started on port " + PORT);
        } catch (IOException e) {
            serverLog.error("server.Server can`t start !!! " + e.getMessage());
        }
    }
}
