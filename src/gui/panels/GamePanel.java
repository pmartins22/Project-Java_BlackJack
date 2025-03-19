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


        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.add(GameButtons.getInstance().getContinueButton());
        buttonPanel.add(GameButtons.getInstance().getStopButton());
        buttonPanel.add(GameButtons.getInstance().getQuitButton());

        panel.add(buttonPanel);

    }

    public static void addToTablePanel(String table, Card card) {
        JPanel cardPanel = new JPanel();
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        cardPanel.add(new JLabel(card.getRank().getSymbol()));
        cardPanel.add(new JLabel(card.getSuit().getSymbol()));
        switch (table) {
            case "Player": GamePanel.getInstance().getPlayerTablePanel().add(cardPanel); break;
            case "Machine": GamePanel.getInstance().getMachineTablePanel().add(cardPanel); break;
        }
        GamePanel.getInstance().updateTotalPanel();
    }

    public void updateTotalPanel() {
        playerTotalPanel.removeAll();
        JLabel playerTotalLabel = new JLabel("Player Total: " + Game.getInstance().getPlayerTotal());
        playerTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerTotalPanel.add(playerTotalLabel);
        machineTotalPanel.removeAll();
        JLabel machineTotalLabel = new JLabel("Machine Total: " + Game.getInstance().getMachineTotal());
        machineTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        machineTotalPanel.add(machineTotalLabel);
    }

    public JPanel getPlayerTablePanel() {
        return playerTablePanel;
    }

    public JPanel getMachineTablePanel() {
        return machineTablePanel;
    }

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
