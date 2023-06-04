import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private JLabel title; //Make logo Owen created
    private JButton startStop;
    private JButton reset;
    //add speed slider somewhere

    private GameBoard gameBoard;

    private boolean isRunning;
    private Timer simTimer;

    public Game() {
        isRunning = false;

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

    public void toggleIsRunning() {
        setIsRunning(!isRunning);
    }

    public void setIsRunning(boolean b) {
        if (b) {
            this.startStop.setText("Stop");
            this.startSimulation();
        }
        else {
            this.stopSimulation();
            this.startStop.setText("Start");
        }
        this.isRunning = b;
    }

    private void startSimulation() {
        simTimer = new Timer();
        simTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                gameBoard.simulateGeneration();
            }
        }, 0, 250);
    }

    private void stopSimulation() {
        if(simTimer != null) simTimer.cancel();
    }

    private void startStopClicked() {
        this.toggleIsRunning();
        System.out.println(isRunning);
    }

    private void resetGame() {
        gameBoard.clearBoard();
        System.out.println("reset!");
    }
}