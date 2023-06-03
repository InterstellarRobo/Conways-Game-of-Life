import javax.swing.*;
import java.awt.*;

public class Game {
    private JFrame f; //Should this be in the constructor only?

    private JPanel topBar;
    private JPanel mainPanel;

    private JLabel title; //Make logo Owen created
    private JButton startStop;
    private JButton reset;
    //add speed slider somewhere

    private JCheckBox[][] gameBoard; //Where the fun begins

    public Game() {
        f = new JFrame("Conway's Game of Life :)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        topBar = new JPanel();
        topBar.setLayout(new FlowLayout());
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(10, 10, 0, 0));

        title = new JLabel("CGOL!");
        topBar.add(title);
        startStop = new JButton("Start");
        startStop.setFocusable(false);
        topBar.add(startStop);
        reset = new JButton("Reset");
        reset.setFocusable(false);
        topBar.add(reset);

        startStop.addActionListener((e) -> this.startStopClicked());
        reset.addActionListener((e) -> this.resetGame());

        f.add(topBar, BorderLayout.NORTH);
        f.add(mainPanel, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

    private void startStopClicked() {
        System.out.println("click!");
    }

    private void resetGame() {
        System.out.println("reset!");
    }
}
