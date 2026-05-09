package org.example.mainmenu;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class Main {

    static void main(String[] args) throws IOException {

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        List<GameInformation> games = GameRepository.loadGames();

        JFrame frame = new JFrame();
        frame.setTitle("CGE: Card Game Engine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new MenuPanel(games));
        frame.setVisible(true);
    }
}