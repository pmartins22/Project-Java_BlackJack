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
    /** Take a random card from deck and add it to player table. If the player total is bigger than 21 the game ends and the player loses. */
    public static void playerTurn() {
        Card card = Game.getInstance().getDeck().takeRandomCard(); // Take random card from game deck.
        Game.getInstance().addToPlayerTotal(card.getValue()); // Add card value to player total.
        GamePanel.addToTablePanel("Player", card); // Add the card to player table.
        GamePanel.getInstance().repaint(); // Repaint the interface.

        // Verify if player total is bigger than 21. If so, game ends and machine wins.
        if (Game.getInstance().getPlayerTotal() > 21) {
            end("Machine");
        }
    }

    /** Runs machine turn automatically in background for fluid interface update. */
    public static void machineTurn() {
        new SwingWorker<Void, Card>() {

            // Run cards taking in background.
            @Override
            protected Void doInBackground() throws Exception {
                GameButtons.getInstance().allButtonsEnabled(false); // Disable all buttons so player cant interfere.
                while (Game.getInstance().getMachineTotal() < 17) { // Keeps taking cards until total is equal or bigger than 17.
                    Card card = Game.getInstance().getDeck().takeRandomCard(); // Take random card from game deck.
                    Game.getInstance().addToMachineTotal(card.getValue()); // Add card value to machine total.
                    publish(card); // Publish the card to the main thread.
                    Thread.sleep(1000); // Wait one second for fluid card taking.

                    // Verify if machine total is bigger than player total. If so, machine stops taking cards.
                    if (Game.getInstance().getMachineTotal() > Game.getInstance().getPlayerTotal()) {
                        break;
                    }
                }
                return null;
            }

            // Manage cards published in background.
            @Override
            protected void process(List<Card> chunks) {
                Card latestCard = chunks.get(chunks.size() - 1); // Get the card taken by machine published in background.
                GamePanel.addToTablePanel("Machine", latestCard); // Add the card to machine table.
                GamePanel.getInstance().repaint(); // Repaint the interface.
            }

            // Executed when background thread is closed.
            @Override
            protected void done() {
                Game.getResult(); // Verify player and machine totals to tell the winner.
            }
        }.execute();
    }

    /** Compare player and machine totals to tell the winner */
    public static void getResult() {

        // If machine total is bigger than 21, player wins.
        if (Game.getInstance().getMachineTotal() > 21) {
            end("Player");
            return;
        }
        int result = Game.getInstance().getPlayerTotal() - Game.getInstance().getMachineTotal(); // Get difference from player total and machine total.

        // If the difference is positive, means player wins. If negative, means machine wins. If its zero, means draw.
        if (result > 0) {
            end("Player");
        } else if (result < 0) {
            end("Machine");
        } else {
            end("Draw");
        }
    }

    /** Begin end animation and update resultMessage */
    public static void end(String who) {

        // Switch who, that contains the winner, to get end message.
        String endMessage = "";
        switch (who) {
            case "Player": endMessage = "Player wins!"; break;
            case "Machine": endMessage = "Machine wins!"; break;
            case "Draw": endMessage = "Draw!"; break;
        }
        String finalEndMessage = endMessage;

        // Run countdown to 3 in background for fluid interface update.
        new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                GameButtons.getInstance().allButtonsEnabled(false); // Disable all buttons so player cant interfere.

                for (int i = 3; i >= 1; i--) {
                    publish(finalEndMessage + " " + i); // Publish end message with countdown value.
                    Thread.sleep(1000); // Wait a second.
                }
                return null;
            }

            // Manage message published in background.
            @Override
            protected void process(List<String> chunks) {
                Game.getInstance().setResultMessage(chunks.get(chunks.size() - 1)); // Get the message published in background and set it to result message.
                GamePanel.getInstance().updateResultPanel(); // Remove old result message and adds the new one.
                GamePanel.getInstance().repaint(); // Repaint the interface.
            }

            // Executed when background thread is closed.
            @Override
            protected void done() {
                Game.getInstance().resetGame(); // Reset the game and let interface ready for a new one.
                GameButtons.getInstance().repaint(); // Repaint the buttons.
                GamePanel.getInstance().setPanels(); // Reset panels to original configuration.
                GamePanel.getInstance().repaint(); // Repaint the interface.

                // Reset buttons to initial configuration.
                GameButtons.getInstance().buttonEnabled("Continue", true);
                GameButtons.getInstance().buttonEnabled("Stop", false);
                GameButtons.getInstance().buttonEnabled("Quit", true);
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
        GameButtons.getInstance().buttonEnabled("Stop", false);
    }
}
