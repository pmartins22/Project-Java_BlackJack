package gui.panels;

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
    private Integer playerTotal = 0;
    private JPanel resultPanel;
    private JPanel machinePanel;
    private JPanel machineTablePanel;
    private JPanel machineTotalPanel;
    private Integer machineTotal = 0;


    private GamePanel() {
        panel = new JPanel(new GridLayout(2, 1));

        // interface panel creation and layout organization
        interfacePanel = new JPanel(new GridLayout(1, 3));

        playerPanel = new JPanel(new GridLayout(2, 1));
        playerTablePanel = new JPanel(new FlowLayout());
        playerTotalPanel = new JPanel(new GridLayout(1, 1));
        JLabel playerTotalLabel = new JLabel(playerTotal.toString());
        playerTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerTotalPanel.add(playerTotalLabel);
        playerPanel.add(playerTablePanel);
        playerPanel.add(playerTotalPanel);

        resultPanel = new JPanel(new GridLayout(1, 1));

        machinePanel = new JPanel(new GridLayout(2, 1));
        machineTablePanel = new JPanel(new FlowLayout());
        machineTotalPanel = new JPanel(new GridLayout(1, 1));
        JLabel machineTotalLabel = new JLabel(machineTotal.toString());
        machineTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        machineTotalPanel.add(machineTotalLabel);
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
