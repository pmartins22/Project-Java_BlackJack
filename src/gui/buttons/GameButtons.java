package gui.buttons;

import domain.Game;
import gui.frames.MainFrame;
import gui.panels.GamePanel;

import javax.swing.*;

public class GameButtons {
    private static GameButtons instance;
    private JButton continueButton;
    private JButton stopButton;
    private JButton quitButton;

    private GameButtons() {
        continueButton = new JButton("Continue");
        continueButton.setHorizontalAlignment(SwingConstants.CENTER);
        continueButton.addActionListener(e -> {
            try {
                Game.playerTurn();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });

        stopButton = new JButton("Stop");
        stopButton.setHorizontalAlignment(SwingConstants.CENTER);
        stopButton.addActionListener(e -> {});

        quitButton = new JButton("Quit");
        quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.addActionListener(e -> {
            Game.getInstance().resetGame();
            System.out.println(Game.getInstance().getPlayerTotal());
            MainFrame.getInstance().navigateToMenu();
        });
    }

    public static GameButtons getInstance() {
        if (instance == null) {
            instance = new GameButtons();
        }
        return instance;
    }

    public JButton getContinueButton() {
        return continueButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JButton getQuitButton() {
        return quitButton;
    }
}
