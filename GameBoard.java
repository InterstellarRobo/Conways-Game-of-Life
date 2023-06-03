import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameBoard extends JPanel implements ActionListener{
    JButton[][] buttonArray;

    public GameBoard(int width, int height) {
        this.setLayout(new GridLayout(height, width, 0, 0));
        
        buttonArray = new JButton[height][width];
        for (int row = 0; row < buttonArray.length; row++) for (int col = 0; col < buttonArray[0].length; col++) {
            JButton b = buttonArray[row][col];
            b = new JButton();
            b.setPreferredSize(new Dimension(16, 16));
            b.putClientProperty(Location.ROW, row);
            b.putClientProperty(Location.COL, col);
            b.addActionListener(this);
            this.add(b);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private enum Location{
        ROW, COL
    }
}
