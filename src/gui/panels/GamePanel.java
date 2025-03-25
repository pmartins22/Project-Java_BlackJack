package gui.panels;

import domain.Card;
import domain.Game;
import gui.buttons.GameButtons;

import javax.swing.*;
import java.awt.*;

public class GamePanel {
    private static GamePanel instance;
    private JPanel panel;
    private JPanel interfacePanel;
    private JPanel playerPanel;
    private JPanel playerTablePanel;
    private JPanel playerTotalPanel;
    private JPanel resultPanel;
    private JPanel machinePanel;
    private JPanel machineTablePanel;
    private JPanel machineTotalPanel;
    private JPanel buttonPanel;



    private GamePanel() {
        panel = new JPanel(new GridLayout(2, 1));

        // interface panel creation and layout organization
        interfacePanel = new JPanel(new GridLayout(1, 3));

        playerPanel = new JPanel(new GridLayout(2, 1));
        playerTablePanel = new JPanel(new FlowLayout());
        playerTotalPanel = new JPanel(new GridLayout(1, 1));

        playerPanel.add(playerTablePanel);
        playerPanel.add(playerTotalPanel);

        resultPanel = new JPanel(new GridLayout(1, 1));

        machinePanel = new JPanel(new GridLayout(2, 1));
        machineTablePanel = new JPanel(new FlowLayout());
        machineTotalPanel = new JPanel(new GridLayout(1, 1));

        machinePanel.add(machineTablePanel);
        machinePanel.add(machineTotalPanel);

        interfacePanel.add(playerPanel);
        interfacePanel.add(resultPanel);
        interfacePanel.add(machinePanel);

        panel.add(interfacePanel);

        // button panel creation and layout organization
        buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(GameButtons.getInstance().getContinueButton());
        buttonPanel.add(GameButtons.getInstance().getStopButton());
        buttonPanel.add(GameButtons.getInstance().getQuitButton());

        panel.add(buttonPanel);

    }

    /** Add card panel to chosen table */
    public static void addToTablePanel(String table, Card card) {
        JPanel cardPanel = new JPanel(); // Create card panel.

        // Add style to card panel.
        cardPanel.setBorder(BorderFactory.createLineBorder(card.getColor())); // Add border to panel.

        JLabel rank = new JLabel(card.getRank().getSymbol()); // Create rank label.
        rank.setFont(new Font("Arial", Font.BOLD, 16)); // Set rank label font.
        rank.setForeground(card.getColor()); // Set rank label color.

        JLabel suit = new JLabel(card.getSuit().getSymbol()); // Create suit label.
        suit.setFont(new Font("Arial", Font.BOLD, 16)); // Set suit label font.
        suit.setForeground(card.getColor()); // Set suit label color.

        cardPanel.add(rank); // Add rank label to card panel.
        cardPanel.add(suit); // Add suit label to card panel.

        // Add card panel to chosen table.
        switch (table) {
            case "Player": GamePanel.getInstance().getPlayerTablePanel().add(cardPanel); break;
            case "Machine": GamePanel.getInstance().getMachineTablePanel().add(cardPanel); break;
        }
        GamePanel.getInstance().updateTotalPanel();
    }

    /** Remove all elements from total panel and add updated value */
    public void updateTotalPanel() {
        // Player total.
        playerTotalPanel.removeAll();
        JLabel playerTotalLabel = new JLabel("Player Total: " + Game.getInstance().getPlayerTotal());
        playerTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerTotalPanel.add(playerTotalLabel);

        // Machine total.
        machineTotalPanel.removeAll();
        JLabel machineTotalLabel = new JLabel("Machine Total: " + Game.getInstance().getMachineTotal());
        machineTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        machineTotalPanel.add(machineTotalLabel);
    }

    /** Remove all elements from result panel and add updated value */
    public void updateResultPanel() {
        resultPanel.removeAll();
        JLabel resultLabel = new JLabel(Game.getInstance().getResultMessage());
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultPanel.add(resultLabel);
    }

    public JPanel getPlayerTablePanel() {
        return playerTablePanel;
    }

    public JPanel getMachineTablePanel() {
        return machineTablePanel;
    }

    /** Set all panels to initial values */
    public void setPanels() {
        playerTablePanel.removeAll();
        playerTotalPanel.removeAll();
        resultPanel.removeAll();
        machineTablePanel.removeAll();
        machineTotalPanel.removeAll();
        JLabel playerTotalLabel = new JLabel("Player Total: " + Game.getInstance().getPlayerTotal());
        playerTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerTotalPanel.add(playerTotalLabel);
        JLabel machineTotalLabel = new JLabel("Machine Total: " + Game.getInstance().getMachineTotal());
        machineTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        machineTotalPanel.add(machineTotalLabel);
    }

    /** Repaint all the panels */
    public void repaint() {
        playerPanel.revalidate();
        playerPanel.repaint();
        playerTablePanel.revalidate();
        playerTablePanel.repaint();
        playerTotalPanel.revalidate();
        playerTotalPanel.repaint();
        resultPanel.revalidate();
        resultPanel.repaint();
        machinePanel.revalidate();
        machinePanel.repaint();
        machineTablePanel.revalidate();
        machineTablePanel.repaint();
        machineTotalPanel.revalidate();
        machineTotalPanel.repaint();
        interfacePanel.revalidate();
        interfacePanel.repaint();
        panel.revalidate();
        panel.repaint();
    }


    public static GamePanel getInstance() {
        if (instance == null) {
            instance = new GamePanel();
        }
        return instance;
    }
    public JPanel getPanel() {
        return panel;
    }
}
