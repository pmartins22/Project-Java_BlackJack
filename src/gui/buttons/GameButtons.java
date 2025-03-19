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
            Game.playerTurn();
            GameButtons.getInstance().buttonEnabled("Stop", true);
        });

        stopButton = new JButton("Stop");
        stopButton.setHorizontalAlignment(SwingConstants.CENTER);
        stopButton.addActionListener(e -> {
            Game.machineTurn();
        });

        quitButton = new JButton("Quit");
        quitButton.setHorizontalAlignment(SwingConstants.CENTER);
        quitButton.addActionListener(e -> {
            Game.getInstance().resetGame();
            System.out.println(Game.getInstance().getPlayerTotal());
            MainFrame.getInstance().navigateToMenu();
        });
    }

    public void buttonEnabled(String buttonName, boolean enabled) {
        switch (buttonName) {
            case "Continue": continueButton.setEnabled(enabled); break;
            case "Stop": stopButton.setEnabled(enabled); break;
            case "Quit": quitButton.setEnabled(enabled); break;
        }
    }

    public void allButtonsEnabled(Boolean enabled) {
        continueButton.setEnabled(enabled);
        stopButton.setEnabled(enabled);
        quitButton.setEnabled(enabled);
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

    public void repaint() {
        continueButton.revalidate();
        continueButton.repaint();
        stopButton.revalidate();
        stopButton.repaint();
        quitButton.revalidate();
        quitButton.repaint();
    }
}
