package secrethitler;

import java.io.*;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GamePanel2 extends JPanel {

    private JLabel fasBoard;
    private JLabel libBoard;

    public GamePanel2() {
        fasBoard.setIcon(new ImageIcon("fasboard.png"));
        libBoard.setIcon(new ImageIcon("libboard.png"));
        fasBoard.setLocation(10, 110);
        libBoard.setLocation(10, 237);
    }
}
