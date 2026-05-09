package org.example.cardgames.war;

import org.example.engine.Game;

import javax.swing.*;
import java.awt.*;

public class warGame implements Game {
    /**
     * @param frame the window that the game will live in
     */
    @Override public void launch(JFrame frame) {
        frame.setTitle("War");
        frame.getContentPane().removeAll();
        frame.getContentPane().add(mainPanel());
        frame.revalidate();
        frame.repaint();
    }

    /**
     * @return the game's name
     */
    @Override public String getName() {
        return "War";
    }

    public JPanel mainPanel() {
        JPanel warPanel = new JPanel();
        warPanel.setLayout(new BorderLayout(5, 5));
        return warPanel;
    }
}
