package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;

public class ReadFromServer implements Runnable {
    private BufferedReader in;

    public ReadFromServer(BufferedReader in) {
        this.in = in;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String answer = in.readLine();
                System.out.println(answer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
