package domain;

import gui.frames.MainFrame;
import gui.panels.GamePanel;

import javax.swing.*;
import java.util.List;
import java.util.Timer;

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

    public static void lost(String who) {
        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                for (int i = 3; i >= 1; i--) {
                    publish(who + " lost\n     " + i);
                    Thread.sleep(1000);
                }
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                Game.getInstance().setResultMessage(chunks.get(chunks.size() - 1));
                GamePanel.getInstance().updateResultPanel();
                GamePanel.getInstance().repaint();
            }

            @Override
            protected void done() {
                Game.getInstance().resetGame();
                MainFrame.getInstance().navigateToMenu();
            }
        }.execute();
    }

    public void setResultMessage(String message) {
        resultMessage = message;
    }

    public String getResultMessage() {
        return resultMessage;
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

    public void resetGame(){
        this.deck = new DeckOfCards();
        this.playerTotal = 0;
        this.machineTotal = 0;
    }
}
