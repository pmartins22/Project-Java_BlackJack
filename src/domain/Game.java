package domain;

import gui.frames.MainFrame;
import gui.panels.GamePanel;

import java.util.List;

public class Game {
    private static Game game;
    private DeckOfCards deck;
    private Integer playerTotal;
    private Integer machineTotal;
    private String resultMessage;

    private Game() {
        deck = new DeckOfCards();
        playerTotal = 0;
        machineTotal = 0;
    }

    public static void playerTurn() throws InterruptedException {
        Card card = Game.getInstance().getDeck().takeRandomCard();
        Game.getInstance().addToPlayerTotal(card.getValue());
        GamePanel.addToTablePanel("Player", card);
        GamePanel.getInstance().repaint();
        if (Game.getInstance().getPlayerTotal() > 21) {
            lost("Player");
        }
    }

    public static void lost(String who) throws InterruptedException {
        switch (who) {
            case "Player":
                for (int i = 3; i >= 1; i--) {
                    Game.getInstance().setResultMessage("Player lost\n     " + i);
                    Thread.sleep(1000);
                }

                break;
            case "Machine":
                for (int i = 3; i >= 1; i--) {
                    Game.getInstance().setResultMessage("Machine lost\n     " + i);
                    Thread.sleep(1000);
                }
                break;
        }
        Game.resetGame();
        MainFrame.getInstance().navigateToMenu();
    }

    public void setResultMessage(String message) {
        resultMessage = message;
    }

    public void addToPlayerTotal(Integer value) {
        playerTotal += value;
    }
    public Integer getPlayerTotal() {
        return playerTotal;
    }

    public void addToMachineTotal(Integer value) {
        machineTotal += value;
    }
    public Integer getMachineTotal() {
        return machineTotal;
    }

    public DeckOfCards getDeck() {
        return deck;
    }

    public static Game getInstance() {
        if (game == null) {
            game = new Game();
        }
        return game;
    }

    public static void resetGame(){
        game = null;
    }
}
