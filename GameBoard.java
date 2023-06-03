import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel{
    public GameBoard(int width, int height) {
        this.setLayout(new GridLayout(height, width, 0, 0));
        this.add(new JButton("Test"));
    }
}
