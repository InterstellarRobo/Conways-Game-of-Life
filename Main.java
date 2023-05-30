import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel o = new JLabel("Conway's Game of Life :)");
        f.add(o);
        f.pack();
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}