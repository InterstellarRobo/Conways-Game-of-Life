import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private JLabel title; //Make logo Owen created
    private JButton startStop;
    private JButton reset;
    private JSlider speed;

    private GameBoard gameBoard;

    private boolean isRunning;
    private Timer simTimer;
    private int speedMultiplier;

    public Game() {
        this(20, 20);
    }

    public Game(int width, int height) {
        isRunning = false;

        JFrame f = new JFrame("Conway's Game of Life :)");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());

        JPanel topBar = new JPanel();
        topBar.setBackground(Color.BLACK);
        topBar.setLayout(new BorderLayout());

        JPanel topLeft = new JPanel();
        topLeft.setOpaque(false);
        topLeft.setLayout(new FlowLayout(FlowLayout.LEADING));
        topBar.add(topLeft, BorderLayout.WEST);

        JPanel topRight = new JPanel();
        topRight.setOpaque(false);
        topRight.setLayout(new FlowLayout(FlowLayout.TRAILING));
        topBar.add(topRight, BorderLayout.EAST);
        

        title = new JLabel("CGOL!");
        title.setForeground(Color.WHITE);
        topLeft.add(title);

        startStop = new JButton("Start");
        startStop.setFocusable(false);
        topRight.add(startStop);

        reset = new JButton("Reset");
        reset.setFocusable(false);
        topRight.add(reset);

        JLabel slowerLabel = new JLabel(scale(24, 24, new ImageIcon("icons/turtle.png")));
        JLabel fasterLabel = new JLabel(scale(24, 24, new ImageIcon("icons/hare.png")));

        speed = new JSlider(25, 475, 250);
        speed.setInverted(true);
        speed.setBackground(Color.BLACK);
        speed.setPaintLabels(true);
        Hashtable<Integer, JLabel> speedLabels = new Hashtable<Integer, JLabel>();
        speedLabels.put(25, fasterLabel);
        speedLabels.put(475, slowerLabel);
        speed.setLabelTable(speedLabels);
        speedMultiplier = speed.getValue();
        topRight.add(speed, 0);

        startStop.addActionListener((e) -> this.startStopClicked());
        reset.addActionListener((e) -> this.resetGame());
        speed.addChangeListener((e) -> this.speedMultiplier = speed.getValue());

        gameBoard = new GameBoard(width, height);

        f.add(topBar, BorderLayout.NORTH);
        f.add((JPanel) gameBoard, BorderLayout.CENTER);
        startStop.setPreferredSize(reset.getPreferredSize()); //keeps start stop button size from changing
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
        this.reset.setEnabled(!b);
        this.isRunning = b;
    }

    private void startSimulation() {
        simTimer = new Timer();
        simTimer.scheduleAtFixedRate(new TimerTask() {
            private int c = 0;
            @Override
            public void run() {
                if(c % speedMultiplier == 0) {
                    gameBoard.simulateGeneration();
                    c = 0;
                }
                c++;
            }
        }, 0, 1);
    }

    private void stopSimulation() {
        if(simTimer != null) simTimer.cancel();
    }

    private void startStopClicked() {
        this.toggleIsRunning();
    }

    private void resetGame() {
        gameBoard.clearBoard();
    }

    private ImageIcon scale(int height, int width, ImageIcon image) {
        return new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
    }
}