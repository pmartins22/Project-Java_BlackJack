package gui.buttons;

import domain.Game;
import gui.frames.MainFrame;
import gui.panels.GamePanel;

import javax.swing.*;

public class MenuButtons {
    private static MenuButtons instance;
    private JButton startGame;


    private MenuButtons() {
        // Create start game button.
        startGame = new JButton("Star Game");
        startGame.setHorizontalAlignment(SwingConstants.CENTER);
        startGame.addActionListener(e -> {
            Game.getInstance().resetGame();
            GamePanel.getInstance().setPanels();
            MainFrame.getInstance().navigateToGame();
        });
    }

    public static MenuButtons getInstance() {
        if (instance == null) {
            instance = new MenuButtons();
        }
        return instance;
    }
    public JButton getStartGame() {
        return startGame;
    }
}
