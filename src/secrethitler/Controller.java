/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secrethitler;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author radloff_859936
 */
public class Controller {
    private ArrayList<String> deck;
    private ArrayList<String> discard;
    private ArrayList<String> players;
    private ArrayList<Integer> role;
    private ArrayList<Integer> FBoard;
    private ArrayList<Integer> LBoard;
    
    public Controller() {
        deck = new ArrayList<>();
        players = new ArrayList<>();
        role = new ArrayList<>();
        discard = new ArrayList<>();
        FBoard = new ArrayList<>();
        LBoard = new ArrayList<>();
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
        while (!role.contains(2)){}
    }
}
