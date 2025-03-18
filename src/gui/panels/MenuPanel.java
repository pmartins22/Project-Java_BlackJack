package gui.panels;

import gui.buttons.MenuButtons;

import javax.swing.*;
import java.awt.*;

public class MenuPanel {
    private static MenuPanel instance;
    private JPanel panel;
    private MenuPanel() {
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        JLabel title = new JLabel("BlackJack");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(title);
        panel.add(MenuButtons.getInstance().getStartGame());
    }
    public static MenuPanel getInstance() {
        if (instance == null) {
            instance = new MenuPanel();
        }
        return instance;
    }

    public JPanel getPanel() {
        return panel;
    }
}
