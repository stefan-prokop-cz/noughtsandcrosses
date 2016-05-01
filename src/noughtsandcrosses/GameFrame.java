/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noughtsandcrosses;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * Here you can specify your class
 *
 * GameFrame.java
 *
 * @version 1.0
 * @author Štefan Prokop <prokoste@fit.cvut.cz>
 * @since 28.4.2016
 */
public class GameFrame extends JFrame {

    static final int NUM_OF_ROWS = 20;
    static final int NUM_OF_COLS = 20;

    private JTextArea taLog;
    private JRadioButton rbCross;
    private JRadioButton rbCircle;
    
    protected static GameButton[][] playGrid = new GameButton[NUM_OF_ROWS][NUM_OF_COLS];

    public GameFrame(String title) throws HeadlessException {
        super(title);
    }

    public void initGUI() {
        // herni pole
        JPanel pnlGrid = new JPanel(new GridLayout(NUM_OF_ROWS, NUM_OF_COLS));
        // postranni panel
        JPanel pnlControls = new JPanel();
        pnlControls.setLayout(new BoxLayout(pnlControls, BoxLayout.Y_AXIS));
        // spodni logovaci panel a jeho obsah
        taLog = new JTextArea("Log: \n");
        taLog.setEditable(false);
        taLog.setRows(5);
        JScrollPane spLog = new JScrollPane(taLog);

        // obsah postranniho panelu
        JPanel pnlControlsShape = new JPanel();
        pnlControlsShape.setLayout(new BoxLayout(pnlControlsShape, BoxLayout.Y_AXIS));
        pnlControlsShape.setBorder(BorderFactory.createTitledBorder("Shape"));

        rbCross = new JRadioButton("Cross");
        rbCircle = new JRadioButton("Circle");
        rbCross.setEnabled(false);
        rbCircle.setEnabled(false);
        rbCross.setSelected(true);

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rbCross);
        btnGroup.add(rbCircle);

        pnlControlsShape.add(rbCross);
        pnlControlsShape.add(rbCircle);

        // obsah herniho panelu
        GameActionListener gaListener = new GameActionListener();
        for (int r = 0; r < NUM_OF_ROWS; r++) {
            for (int c = 0; c < NUM_OF_COLS; c++) {
                playGrid[r][c] = new GameButton(r, c);
                playGrid[r][c].addActionListener(gaListener);
                pnlGrid.add(playGrid[r][c]);
            }
        }

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        JMenuItem menuItem = new JMenuItem("New");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        menuItem.addActionListener((ActionEvent e) -> {
            for (int r = 0; r < NUM_OF_ROWS; r++) {
                for (int c = 0; c < NUM_OF_COLS; c++) {
                    playGrid[r][c].setEnabled(true);
                    playGrid[r][c].setShape(GameButton.Shape.NONE);
                }
            }
        });

        // vlozeni do GameFrame
        pnlControls.add(pnlControlsShape);
        add(pnlGrid, BorderLayout.CENTER);
        add(pnlControls, BorderLayout.EAST);
        add(spLog, BorderLayout.SOUTH);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setResizable(false);
        
        this.setLocationRelativeTo(null);
    }

    public void log(String message) {
        taLog.append(message + "\n");
        // scrollbar jezdi podle toho, kolik je radku (vzdy nakonec)
        taLog.setCaretPosition(taLog.getText().length());
    }

    public boolean isCrossTurn() {
        return rbCross.isSelected();
    }

    public void setCrossTurn() {
        rbCross.setSelected(true);
    }

    public void setCircleTurn() {
        rbCircle.setSelected(true);
    }
}
