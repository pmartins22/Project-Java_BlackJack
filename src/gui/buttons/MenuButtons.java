package gui.buttons;

import gui.frames.MainFrame;

import javax.swing.*;

public class MenuButtons {
    private static MenuButtons instance;
    private JButton startGame;


    private MenuButtons() {
        startGame = new JButton("Star Game");
        startGame.setHorizontalAlignment(SwingConstants.CENTER);
        startGame.addActionListener(e -> {
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
