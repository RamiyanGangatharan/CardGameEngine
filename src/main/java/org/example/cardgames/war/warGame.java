package org.example.cardgames.war;

import org.example.engine.Game;
import org.example.engine.PauseMenu;
import org.example.mainmenu.GameRepository;
import org.example.mainmenu.MenuPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class warGame implements Game, KeyListener {
    private PauseMenu pauseMenu;

    /**
     * @param frame the window that the game will live in
     */
    @Override
    public void launch(JFrame frame) throws IOException {
        frame.setTitle("War");
        pauseMenu = new PauseMenu(frame, () -> returnToMenu(frame));
        warLogic logic = new warLogic();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(mainPanel());
        frame.addKeyListener(this);
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.setBackground(Color.GRAY);
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

    public JPanel mainPanel() throws IOException {
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));

        JPanel gamePanel = new JPanel(new GridBagLayout());
        gamePanel.setBackground(new Color(34, 139, 34));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        Deck deck = new Deck();

        Card p1Card = deck.drawCard();
        Card p2Card = deck.drawCard();

        JLabel centerCard1 = createCardLabel(p1Card);
        JLabel centerCard2 = createCardLabel(p2Card);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gamePanel.add(centerCard1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gamePanel.add(centerCard2, gbc);

        JPanel handPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        handPanel.setBackground(new Color(20, 90, 20));

        ArrayList<Card> hand = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Card card = deck.drawCard();
            hand.add(card);
            handPanel.add(createCardLabel(card));
        }

        mainPanel.add(gamePanel, BorderLayout.CENTER);
        mainPanel.add(handPanel, BorderLayout.SOUTH);

        return mainPanel;
    }

    private JLabel createCardLabel(Card card) throws IOException {
        String path = card.getImagePath();
        System.out.println("Loading: " + path);

        var url = getClass().getResource(path);
        if (url == null) {
            throw new IllegalStateException("Missing image resource: " + path);
        }

        BufferedImage image = ImageIO.read(url);
        Image scaled = image.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled));
    }

    private JLabel createCardBackLabel() throws IOException {
        var url = getClass().getResource("/deckOfCards/back.jpg");
        if (url == null) {
            throw new IllegalStateException("Missing image resource: /deckOfCards/back.jpg");
        }

        BufferedImage image = ImageIO.read(url);
        Image scaled = image.getScaledInstance(100, 150, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(scaled));
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
