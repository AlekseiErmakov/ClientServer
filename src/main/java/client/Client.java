package client;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {
    private final int PORT = 19000;
    private final String HOST = "localhost";

    private Socket socket;

    public Client() {
        try {
            socket = new Socket(HOST, PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new PrintWriter(socket.getOutputStream()))) {
             startListen(consoleReader, in, out);
             join();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void startListen(BufferedReader consoleReader, BufferedReader in, BufferedWriter out) {
        ReadFromServer readFromServer = new ReadFromServer(in);
        WriteToServer writeToServer = new WriteToServer(consoleReader, out);
        start(readFromServer);
        start(writeToServer);
    }

    private <T extends Runnable> void start(T thread) {
        Thread outThread = new Thread(thread);
        outThread.setDaemon(true);
        outThread.start();
    }


}
