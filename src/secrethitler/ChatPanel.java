/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secrethitler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ryan
 */
public class ChatPanel extends javax.swing.JPanel {

    static Socket server;
    OutputStream outToServer;
    DataOutputStream out;
    InputStream inFromServer;
    DataInputStream in;

    /**
     * Creates new form ChatPanel
     * @throws java.io.IOException
     */
    public ChatPanel() throws IOException {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        area_Chat = new javax.swing.JTextArea();
        text_Send = new javax.swing.JTextField();
        button_Send = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(300, 480));
        setMinimumSize(new java.awt.Dimension(300, 480));
        setPreferredSize(new java.awt.Dimension(300, 480));

        area_Chat.setEditable(false);
        area_Chat.setColumns(20);
        area_Chat.setRows(5);
        jScrollPane1.setViewportView(area_Chat);

        button_Send.setText("Send");
        button_Send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SendActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(text_Send, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(button_Send))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(text_Send, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_Send))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button_SendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SendActionPerformed
        try {
            out.writeUTF(text_Send.getText());
            text_Send.setText("");
        } catch (IOException ex) {
            Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_button_SendActionPerformed

    public void join() throws IOException {
        server = new Socket("localhost", 59696);
        outToServer = server.getOutputStream();
        out = new DataOutputStream(outToServer);
        inFromServer = server.getInputStream();
        in = new DataInputStream(inFromServer);
        Checker boi = new Checker();
        boi.start();
    }
    
    class Checker extends Thread {
        public void run() {
            while (1 == 1) {
                try {
                    area_Chat.append(in.readUTF() + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(ChatBox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area_Chat;
    private javax.swing.JButton button_Send;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField text_Send;
    // End of variables declaration//GEN-END:variables
}