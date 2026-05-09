package org.example.engine;

import javax.swing.*;
import java.awt.*;

public class PauseMenu {
    private boolean isPaused = false;
    private final JPanel pausePanel;

    public PauseMenu(JFrame frame, Runnable onReturnMenu) {
        pausePanel = new JPanel();
        pausePanel.setLayout(new GridBagLayout());
        pausePanel.setBackground(Color.LIGHT_GRAY);
        pausePanel.setVisible(false);

        JButton resumeBtn = new JButton("Resume");
        resumeBtn.addActionListener(e -> togglePause());
        pausePanel.add(resumeBtn);

        JButton returnToMenuBtn = new JButton("Return to Menu");
        returnToMenuBtn.addActionListener(e -> {
                    onReturnMenu.run();
                    togglePause(); // temp fix for when changing window states
                }
        );
        pausePanel.add(returnToMenuBtn);

        JLayeredPane layeredPane = frame.getRootPane().getLayeredPane();
        layeredPane.add(pausePanel, JLayeredPane.POPUP_LAYER);

        // Set bounds after frame is visible so dimensions are correct
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                pausePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
            }
        });

        // Set initial bounds
        pausePanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    }

    public void togglePause() {
        isPaused = !isPaused;
        pausePanel.setVisible(isPaused);
        pausePanel.repaint();
    }
}