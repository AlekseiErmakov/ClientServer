package client;

import java.io.*;

public class WriteToServer implements Runnable {
    private BufferedReader in;
    private BufferedWriter out;


    public WriteToServer(BufferedReader in, BufferedWriter out) {
        this.in = in;
        this.out = out;

    }

    @Override
    public void run() {
        while (true) {
            try {
                String text = in.readLine();
                out.write(text);
                out.newLine();
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
