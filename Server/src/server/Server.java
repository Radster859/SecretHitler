/*
 * Properties.changeTothislicenseheader,chooseLicenseHeadersinProject          
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author radloff_859936
 */
public class Server extends Thread {

    public static ServerSocket sock;
    public static ArrayList<Socket> clients = new ArrayList<>();
    public static ArrayList<DataInputStream> inputs = new ArrayList<>();
    public static ArrayList<DataOutputStream> outputs = new ArrayList<>();

    /**   
     */
    public Server(int port) throws IOException {
        sock = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            String text = "";
            System.out.println(clients.size());
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).isOutputShutdown()) {
                    clients.remove(i);
                    inputs.remove(i);
                    outputs.remove(i);
                }
            }
            for (int i = 0; i < clients.size(); i++) {
                try {
                    if (inputs.get(i).available() > 0) {
                        text = inputs.get(i).readUTF();
                        for (int j = 0; j < clients.size(); j++) {
                            outputs.get(j).writeUTF(i + 1 + " - " + text);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        //int port = 62420;
        int port = 59696;
        try {
            Thread t = new Server(port);
            t.start();
            Socket temp;
            while (1 == 1) {
                temp = sock.accept();
                clients.add(temp);
                inputs.add(new DataInputStream(temp.getInputStream()));
                outputs.add(new DataOutputStream(temp.getOutputStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
