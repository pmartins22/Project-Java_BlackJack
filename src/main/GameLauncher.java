package main;

import gui.frames.MainFrame;

public class GameLauncher {

    public static void main(String[] args) {
        launchGame();
    }

    private static void launchGame() {
        MainFrame.getInstance().getFrame().setVisible(true);
    }

}
