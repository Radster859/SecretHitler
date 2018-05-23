/*
 * To change this license header, choose License Headers in Project Properties.
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
     * @param args the command line arguments
     */
    public Server(int port) throws IOException {
        sock = new ServerSocket(port);
    }
    
    public void run() {
      while(true) {
            String text = "";
            System.out.println(clients.size());
            for (int i = 0; i < clients.size(); i++) {
                try {
                    System.out.println("" + i + inputs.get(i).available());
                    if (inputs.get(i).available() > 0) {
                        text = inputs.get(i).readUTF();
                        System.out.print(text + "!");
                        for (int j = 0; j < clients.size(); j++) {
                            outputs.get(j).writeUTF(text);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
      }
   }
    public static void main(String[] args) throws IOException {
        int port = 62420;
      try {
         Thread t = new Server(port);
         t.start();
         Socket temp;
         while (1 == 1) {
             temp = sock.accept();
             System.out.println("ACCEPTED");
             clients.add(temp);
             inputs.add(new DataInputStream(temp.getInputStream()));
             outputs.add(new DataOutputStream(temp.getOutputStream()));
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
    }
}
