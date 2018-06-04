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
import java.util.Collections;
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
    private static boolean gameOver = false;
    private static ArrayList<String> deck = new ArrayList<>();
    private static ArrayList<String> players = new ArrayList<>();
    private static ArrayList<Integer> role = new ArrayList<>();
    private static ArrayList<String> discard = new ArrayList<>();
    private static ArrayList<Integer> FBoard = new ArrayList<>();
    private static ArrayList<Integer> LBoard = new ArrayList<>();

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
            while (clients.size() < 5) {
                temp = sock.accept();
                clients.add(temp);
                inputs.add(new DataInputStream(temp.getInputStream()));
                outputs.add(new DataOutputStream(temp.getOutputStream()));
            }
            for (int i = 0; i < 11; i++) {
                deck.add("F");
            }
            for (int i = 0; i < 6; i++) {
                deck.add("L");
            }
            players.add("F");
            players.add("H");
            players.add("L");
            players.add("L");
            players.add("L");
            Collections.shuffle(deck);
            Collections.shuffle(players);
            // 1 - President   2 - Chancellor   0 - Normie
            role.add(1);
            role.add(0);
            role.add(0);
            role.add(0);
            role.add(0);
            while (!gameOver) {
                if (!role.contains(2)) {
                    outputs.get(role.indexOf(1)).writeUTF("president");
                    while (inputs.get(role.indexOf(1)).available() == 0) {}
                    role.set(Integer.parseInt(inputs.get(role.indexOf(1)).readUTF()), 2);
                    while (inputs.get(0).available() == 0 && inputs.get(1).available() == 0 && inputs.get(2).available() == 0 && inputs.get(3).available() == 0 && inputs.get(4).available() == 0){}
                    int jas = 0;
                    int neins = 0;
                    for (int i = 0; i < 5; i++) {
                        if(inputs.get(i).readUTF() == "ja") {
                            jas++;
                        } else if (inputs.get(i).readUTF() == "nein") {
                            neins++;
                        }
                    }
                    if (jas>neins) {
                        
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
