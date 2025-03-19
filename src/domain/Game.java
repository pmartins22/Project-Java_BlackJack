package domain;

import gui.buttons.GameButtons;
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

    public static void playerTurn() {
        Card card = Game.getInstance().getDeck().takeRandomCard();
        Game.getInstance().addToPlayerTotal(card.getValue());
        GamePanel.addToTablePanel("Player", card);
        GamePanel.getInstance().repaint();
        if (Game.getInstance().getPlayerTotal() > 21) {
            end("Player");
        }
    }

    public static void machineTurn() {
        new SwingWorker<Void, Card>() {
            @Override
            protected Void doInBackground() throws Exception {
                GameButtons.getInstance().allButtonsEnabled(false);
                while (Game.getInstance().getMachineTotal() < 17) {
                    Card card = Game.getInstance().getDeck().takeRandomCard();
                    Game.getInstance().addToMachineTotal(card.getValue());
                    publish(card);
                    Thread.sleep(1000);
                }
                return null;
            }

            @Override
            protected void process(List<Card> chunks) {
                Card latestCard = chunks.get(chunks.size() - 1);
                GamePanel.addToTablePanel("Machine", latestCard);
                GamePanel.getInstance().repaint();
            }

            @Override
            protected void done() {
                Game.getResult();
            }
        }.execute();
    }

    public static void getResult() {
        if (Game.getInstance().getMachineTotal() > 21) {
            end("Machine");
            return;
        }
        int result = Game.getInstance().getPlayerTotal() - Game.getInstance().getMachineTotal();
        if (result > 0) {
            end("Machine");
        } else if (result < 0) {
            end("Player");
        } else {
            end("Draw");
        }
    }

    public static void end(String who) {
        String endMessage = "";
        switch (who) {
            case "Player": endMessage = "Machine wins!"; break;
            case "Machine": endMessage = "Player wins!"; break;
            case "Draw": endMessage = "Draw!"; break;
        }
        String finalEndMessage = endMessage;
        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                GameButtons.getInstance().allButtonsEnabled(false);
                for (int i = 3; i >= 1; i--) {
                    publish(finalEndMessage + " " + i);
                    Thread.sleep(1000);
                }
                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                Game.getInstance().setResultMessage(chunks.get(chunks.size() - 1));
                GamePanel.getInstance().updateResultPanel();
                GamePanel.getInstance().repaint();
            }

            @Override
            protected void done() {
                Game.getInstance().resetGame();
                GamePanel.getInstance().setPanels();
                GamePanel.getInstance().repaint();
                GameButtons.getInstance().allButtonsEnabled(true);
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
