package org.example.cardgames.placeholder;

import org.example.engine.Game;
import javax.swing.*;

public class PlaceholderGame implements Game {
    private final String name;

    public PlaceholderGame(String name) {
        this.name = name;
    }

    @Override
    public void launch(JFrame frame) {
        JOptionPane.showMessageDialog(frame, name + " is a work in progress.");
    }

    @Override
    public String getName() { return name; }
}