package org.example.cardgames.war;

import org.example.engine.Game;
import org.example.engine.PauseMenu;
import org.example.mainmenu.GameRepository;
import org.example.mainmenu.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class warGame implements Game, KeyListener {
    private PauseMenu pauseMenu;

    /**
     * @param frame the window that the game will live in
     */
    @Override
    public void launch(JFrame frame) {
        frame.setTitle("War");
        pauseMenu = new PauseMenu(frame, () -> returnToMenu(frame));
        frame.getContentPane().removeAll();
        frame.getContentPane().add(mainPanel());
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.revalidate();
        frame.repaint();
    }

    private void returnToMenu(JFrame frame) {
        try {
            frame.getContentPane().removeAll();
            frame.setTitle("CGE: Card Game Engine");
            frame.setContentPane(new MenuPanel(GameRepository.loadGames()));
            frame.revalidate();
            frame.repaint();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Failed to load menu: " + e.getMessage());
        }
    }

    /**
     * @return the game's name
     */
    @Override
    public String name() {
        return "War";
    }

    public JPanel mainPanel() {
        JPanel warPanel = new JPanel();
        warPanel.setLayout(new BorderLayout(5, 5));
        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> pauseMenu.togglePause());
        warPanel.add(pauseButton, BorderLayout.NORTH);
        return warPanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            pauseMenu.togglePause();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
