import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel o = new JLabel("Conway's Game of Life :)");
        JButton b = new JButton("Just testing stuff");
        b.setFocusable(false);

        f.setLayout(new FlowLayout());
        f.add(o);
        f.add(b);
        f.pack();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);

        //this is daniel testing committing changes
    }
}