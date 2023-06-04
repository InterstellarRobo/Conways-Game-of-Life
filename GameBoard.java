import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener{
    private JButton[][] buttonArray;
    private World internalGame;

    public GameBoard(int width, int height) {
        this.setLayout(new GridLayout(height, width, 2, 2));
        Color backgroundColor = Color.BLACK;
        this.setBackground(backgroundColor);
        this.setBorder(BorderFactory.createLineBorder(backgroundColor, 2, false));
        
        buttonArray = new JButton[height][width];
        internalGame = new World(width, height);
        for (int row = 0; row < buttonArray.length; row++) for (int col = 0; col < buttonArray[0].length; col++) {
            buttonArray[row][col] = new JButton();
            JButton b = buttonArray[row][col];
            b.setPreferredSize(new Dimension(16, 16));
            b.setFocusable(false);
            b.putClientProperty(ButtonProperty.ROW, row);
            b.putClientProperty(ButtonProperty.COL, col);
            b.putClientProperty(ButtonProperty.ALIVE, false);
            this.updateButtonState(b);
            b.addActionListener(this);
            this.add(b);
        }
    }

    public void simulateGeneration() {
        internalGame.update();
        this.updateAllButtonStates();
    }

    public void clearBoard() {
        internalGame.depopulateAllCells();
        this.updateAllButtonStates();
    }

    private void paintDead(JButton b) {
        b.setBackground(new Color(0, 0, 128));
        b.setBorder(null);
    }

    private void paintAlive(JButton b) {
        b.setBackground(new Color(179, 0, 163));
        b.setBorder(null);
    }

    private void updateButtonState(JButton b) {
        if (b.getClientProperty(ButtonProperty.ROW) != null && b.getClientProperty(ButtonProperty.COL) != null) {
            if (internalGame.cellStatus((int) b.getClientProperty(ButtonProperty.ROW), (int) b.getClientProperty(ButtonProperty.COL))){ 
                this.paintAlive(b);
            }
            else { 
                this.paintDead(b);
            }
        }
    }

    private void updateAllButtonStates() {
        for (JButton[] q : buttonArray) for (JButton b : q) {
            this.updateButtonState(b);
        }
    }

    private void toggleState(JButton b) {
        if (b.getClientProperty(ButtonProperty.ROW) != null && b.getClientProperty(ButtonProperty.COL) != null) {
            internalGame.flipCell((int) b.getClientProperty(ButtonProperty.ROW), (int) b.getClientProperty(ButtonProperty.COL));
            updateButtonState(b);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            this.toggleState((JButton) e.getSource());
        }
    }

    private enum ButtonProperty{
        ROW, COL, ALIVE
    }
}
