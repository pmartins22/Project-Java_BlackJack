package gui.frames;

import gui.panels.GamePanel;
import gui.panels.MenuPanel;

import javax.swing.*;

public class MainFrame {
    private static MainFrame instance;

    private final JFrame frame;

    private MainFrame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.add(MenuPanel.getInstance().getPanel());
    }

    /** Remove all panels in frame and add game panel to it. */
    public void navigateToGame() {
        frame.getRootPane().getContentPane().removeAll();
        frame.getRootPane().getContentPane().add(GamePanel.getInstance().getPanel());
        SwingUtilities.invokeLater(() -> {
            frame.getRootPane().getContentPane().revalidate();
            frame.getRootPane().getContentPane().repaint();
            GamePanel.getInstance().repaint();
        });
    }

    /** Remove all panels in frame and add menu panel to it. */
    public void navigateToMenu() {
        frame.getRootPane().getContentPane().removeAll();
        frame.getRootPane().getContentPane().add(MenuPanel.getInstance().getPanel());
        SwingUtilities.invokeLater(() -> {
            frame.getRootPane().getContentPane().revalidate();
            frame.getRootPane().getContentPane().repaint();
        });
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
        }
        return instance;
    }
    public JFrame getFrame() {
        return frame;
    }
}
