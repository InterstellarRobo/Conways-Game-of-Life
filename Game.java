import javax.swing.*;
import java.awt.*;

public class Game {
    private JLabel title; //Make logo Owen created
    private JButton startStop;
    private JButton reset;
    //add speed slider somewhere

    private GameBoard gameBoard;

    public Game() {
        JFrame f = new JFrame("Conway's Game of Life :)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        JPanel topBar = new JPanel();
        topBar.setLayout(new FlowLayout());
        

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

        gameBoard = new GameBoard(20, 20);

        f.add(topBar, BorderLayout.NORTH);
        f.add((JPanel) gameBoard, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
    }

    private void startStopClicked() {
        gameBoard.simulateGeneration();
        System.out.println("click!");
    }

    private void resetGame() {
        gameBoard.clearBoard();
        System.out.println("reset!");
    }
}