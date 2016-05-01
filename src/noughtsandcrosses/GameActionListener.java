/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package noughtsandcrosses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * Here you can specify your class
 *
 * GameActionListener.java
 *
 * @version 1.0
 * @author Štefan Prokop <prokoste@fit.cvut.cz>
 * @since 28.4.2016
 */
public class GameActionListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        GameButton btnSource = (GameButton) e.getSource();
        Noughtsandcrosses.game.log("Stisknuto tlacitko [" + btnSource.getRow()
                + ", " + btnSource.getColumn() + "]");

        if (Noughtsandcrosses.game.isCrossTurn()) {
            btnSource.setShape(GameButton.Shape.CROSS);
            Noughtsandcrosses.game.setCircleTurn();
        } else {
            btnSource.setShape(GameButton.Shape.CIRCLE);
            Noughtsandcrosses.game.setCrossTurn();
        }

        btnSource.setEnabled(false);

        if (isWinMove(btnSource.getRow(), btnSource.getColumn())) {
            JOptionPane.showMessageDialog(Noughtsandcrosses.game, "You win!");
            
        }
    }

    // vyhra na 5 policek
    public boolean isWinMove(int row, int column) {
        if (checkLines(row, column)) {
            return true;
        } else if (checkColumns(row, column)) {
            return true;
        } else if (checkDiagonalsFirst(row, column)) {
            return true;
        } else if (checkDiagonalsSecond(row, column)) {
            return true;
        }

        return false;
    }

    // testuje zda je vyhra v radku
    private boolean checkLines(int row, int column) {
        int count = 1;
        int stat = 0;
        
        GameButton.Shape shape;
        
        if(Noughtsandcrosses.game.isCrossTurn()) {
            shape = GameButton.Shape.CIRCLE;
        } else {
            shape = GameButton.Shape.CROSS;
        }
        
        for (int i = 1; i <= 5; i++) {
            if(stat == 0) {
                if(column + i <= GameFrame.NUM_OF_COLS - 1) {
                    if(GameFrame.playGrid[row][column+i].getShape() == shape) {
                        count++;
                    } else {
                        stat = 1;
                        break;
                    }
                } else {
                    stat = 1;
                    break;
                }
            }
        }
        for (int i = 1; i <= 5; i++) {
            if(stat == 1) {
                if(column - i >= 0) {
                    if(GameFrame.playGrid[row][column-i].getShape() == shape) {
                        count++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return count >= 5;
    }

    // testuje zda je vyhra ve sloupci
    private boolean checkColumns(int row, int column) {
        int count = 1;
        int stat = 0;
        
        GameButton.Shape shape;
        
        if(Noughtsandcrosses.game.isCrossTurn()) {
            shape = GameButton.Shape.CIRCLE;
        } else {
            shape = GameButton.Shape.CROSS;
        }
        
        for (int i = 1; i <= 5; i++) {
            if(stat == 0) {
                if(row + i <= GameFrame.NUM_OF_ROWS - 1) {
                    if(GameFrame.playGrid[row+i][column].getShape() == shape) {
                        count++;
                    } else {
                        stat = 1;
                        break;
                    }
                } else {
                    stat = 1;
                    break;
                }
            }
        }
        for (int i = 1; i <= 5; i++) {
            if(stat == 1) {
                if(row - i >= 0) {
                    if(GameFrame.playGrid[row-i][column].getShape() == shape) {
                        count++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        return count >= 5;
    }

    // testuje zda je vyhra v diagonale - smer doleva nahoru + doprava dolu
    private boolean checkDiagonalsFirst(int row, int column) {
        int count = 1;
        int stat = 0;
        int c = 1;
        
        GameButton.Shape shape;
        
        if(Noughtsandcrosses.game.isCrossTurn()) {
            shape = GameButton.Shape.CIRCLE;
        } else {
            shape = GameButton.Shape.CROSS;
        }

        for (int i = 1; i <= 5; i++) {
            if(stat == 0) {
                if(row + i <= GameFrame.NUM_OF_ROWS - 1 &&
                        column + i <= GameFrame.NUM_OF_COLS - 1) {
                    if(GameFrame.playGrid[row+i][column+i].getShape() == shape) {
                        count++;
                    } else {
                        stat = 1;
                        break;
                    }
                } else {
                    stat = 1;
                    break;
                }
            }
        }
        for (int i = 1; i <= 5; i++) {
            if(stat == 1) {
                if(row - i >= 0 && column - i >= 0) {
                    if(GameFrame.playGrid[row-i][column-i].getShape() == shape) {
                        count++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        
        return count >= 5 || c >= 5;
    }

    // testuje zda je vyhra v diagonale - smer doprava nahoru + doleva dolu
    private boolean checkDiagonalsSecond(int row, int column) {
        int count = 1;
        int stat = 0;
        int c = 1;
        
        GameButton.Shape shape;
        
        if(Noughtsandcrosses.game.isCrossTurn()) {
            shape = GameButton.Shape.CIRCLE;
        } else {
            shape = GameButton.Shape.CROSS;
        }

        for (int i = 1; i <= 5; i++) {
            if(stat == 0) {
                if(row + i <= GameFrame.NUM_OF_ROWS - 1 &&
                        column - i >= 0) {
                    if(GameFrame.playGrid[row+i][column-i].getShape() == shape) {
                        count++;
                    } else {
                        stat = 1;
                        break;
                    }
                } else {
                    stat = 1;
                    break;
                }
            }
        }
        for (int i = 1; i <= 5; i++) {
            if(stat == 1) {
                if(row - i >= 0 && column + i <= GameFrame.NUM_OF_COLS - 1) {
                    if(GameFrame.playGrid[row-i][column+i].getShape() == shape) {
                        count++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        
        return count >= 5 || c >= 5;
    }
}
