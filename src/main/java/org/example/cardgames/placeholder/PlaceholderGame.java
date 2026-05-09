package org.example.cardgames.placeholder;

import org.example.engine.Game;

import javax.swing.*;

public record PlaceholderGame(String name) implements Game {

    @Override
    public void launch(JFrame frame) {
        JOptionPane.showMessageDialog(frame, name + " is a work in progress.");
    }
}