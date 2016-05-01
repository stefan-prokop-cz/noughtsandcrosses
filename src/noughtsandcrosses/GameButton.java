/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package noughtsandcrosses;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 * 
 * Here you can specify your class
 *
 * GameButton.java
 *
 * @version 1.0
 * @author Štefan Prokop <prokoste@fit.cvut.cz>
 * @since 28.4.2016
 */
public class GameButton extends JButton {
    private Shape shape;
    private final int row;
    private final int column;
    private static final int OFFSET = 5;
    
    public enum Shape {
        CIRCLE, CROSS, NONE;
    }

    public GameButton(int row, int column) {
        this.row = row;
        this.column = column;
        shape = Shape.NONE;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch(shape) {
            case CROSS:
                g.setColor(Color.blue);
                g.drawLine(OFFSET, OFFSET, getWidth() - OFFSET, getHeight() - OFFSET);
                g.drawLine(getWidth() - OFFSET, OFFSET, OFFSET, getHeight() - OFFSET);
                break;
            case CIRCLE:
                g.setColor(Color.red);
                g.drawOval(OFFSET, OFFSET, getWidth() - 2*OFFSET, getHeight() - 2*OFFSET);
                break;
            default:
                break;
        }
    }

    public Shape getShape() {
        return shape;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
