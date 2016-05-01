/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package noughtsandcrosses;

/**
 *
 * @version 1.0
 * @author Štefan Prokop <prokoste@fit.cvut.cz>
 * @since 28.4.2016
 */
public class Noughtsandcrosses {

    static GameFrame game;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        game = new GameFrame("Noughts and Crosses");
        game.initGUI();
    }

}
