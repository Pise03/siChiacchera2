package com.itismeucci;

import java.io.*;
import java.net.*;

public class ClientListener extends Thread {

    Client client = new Client();
    BufferedReader inDalServer;
    String stringaRicevutaDalServer;
    Socket mioSocket;

    // costruttore
    public ClientListener(Socket socket) throws IOException{
        mioSocket = socket;
        inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream()));
    }

    public void ascolta() throws IOException {
        for (;;) {
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("\n" + "Stringa ricevuta: " + stringaRicevutaDalServer);

            if (stringaRicevutaDalServer.charAt(0) == '$' && stringaRicevutaDalServer.charAt(1) == 'e') {
                System.out.println("Disconnessione...");
                break;
            }
        }
    }

    public void run() {
        try {
            ascolta();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
