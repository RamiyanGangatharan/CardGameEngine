package org.example.mainmenu;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MenuPanel extends JPanel {

    private final List<GameInformation> games;

    /**
     * This code is in charge of the GUI for the main menu.
     *
     * @param games the list of games this will display
     */
    public MenuPanel(List<GameInformation> games) {
        this.games = games;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        add(getTitlePanel(), BorderLayout.NORTH);
        add(getMainPanel(), BorderLayout.CENTER);
    }

    private JPanel getTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());

        JLabel title = new JLabel("CARD GAME ENGINE");
        title.setFont(new Font("Dialog", Font.BOLD, 32));
        title.setForeground(Color.BLACK);

        JLabel subtitle = new JLabel("Ramiyan Gangatharan");
        subtitle.setFont(new Font("Monospaced", Font.PLAIN, 13));
        subtitle.setForeground(Color.GRAY);
        subtitle.setBorder(new EmptyBorder(10, 5, 5, 5));

        titlePanel.add(title, BorderLayout.NORTH);
        titlePanel.add(subtitle, BorderLayout.SOUTH);
        return titlePanel;
    }

    private JPanel getMainPanel() {

        JPanel mainPanel = new JPanel(new BorderLayout(15, 0));

        // Left Side
        DefaultListModel<GameInformation> model = new DefaultListModel<>();

        for (GameInformation game : games) {
            model.addElement(game);
        }

        JList<GameInformation> gamesList = new JList<>(model);
        gamesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gamesList.setFont(new Font("Monospaced", Font.PLAIN, 16));
        gamesList.setFixedCellHeight(45);
        gamesList.setBorder(
                new CompoundBorder(
                        new EmptyBorder(5, 5, 5, 5),
                        new TitledBorder("SELECT A GAME")
                )
        );

        JScrollPane listScrollPane = new JScrollPane(gamesList);
        listScrollPane.setPreferredSize(new Dimension(240, 0));
        listScrollPane.setBorder(BorderFactory.createLineBorder(new Color(70, 70, 70), 1));
        mainPanel.add(listScrollPane, BorderLayout.WEST);

        // Right Side
        JPanel rightPanel = new JPanel(new BorderLayout(15, 15));
        rightPanel.setBorder(new EmptyBorder(5, 10, 5, 5));

        JLabel gameTitle = new JLabel("SELECT A GAME");
        gameTitle.setFont(new Font("Dialog", Font.BOLD, 28));
        gameTitle.setForeground(Color.BLACK);

        JTextPane description = new JTextPane();
        description.setEditable(false);
        description.setFocusable(false);
        description.setBackground(Color.LIGHT_GRAY);
        description.setForeground(Color.BLACK);
        description.setFont(new Font("Monospaced", Font.PLAIN, 15));

        description.setBorder(
                new CompoundBorder(
                        BorderFactory.createLineBorder(new Color(70, 70, 70), 1),
                        new EmptyBorder(15, 15, 15, 15)
                )
        );

        StyledDocument doc = description.getStyledDocument();
        SimpleAttributeSet attr = new SimpleAttributeSet();

        StyleConstants.setLineSpacing(attr, 0.25f);
        doc.setParagraphAttributes(0, doc.getLength(), attr, false);

        JScrollPane descriptionScroll = new JScrollPane(description);
        descriptionScroll.setBorder(null);

        // button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        JButton playButton = getPlayButton(gamesList);

        JButton exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("Dialog", Font.BOLD, 16));
        exitButton.setPreferredSize(new Dimension(120, 45));
        exitButton.addActionListener(_ -> System.exit(0));

        buttonPanel.add(playButton);
        buttonPanel.add(exitButton);

        // listener
        gamesList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                GameInformation selectedGame = gamesList.getSelectedValue();
                if (selectedGame != null) {
                    gameTitle.setText(selectedGame.getTitle().toUpperCase());
                    description.setText(selectedGame.getDescription());
                    description.setCaretPosition(0);
                }
            }
        });


        rightPanel.add(gameTitle, BorderLayout.NORTH);
        rightPanel.add(descriptionScroll, BorderLayout.CENTER);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(rightPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    private JButton getPlayButton(JList<GameInformation> gamesList) {
        JButton playButton = new JButton("PLAY");
        playButton.setFont(new Font("Dialog", Font.BOLD, 16));
        playButton.setPreferredSize(new Dimension(120, 45));
        playButton.addActionListener(_ -> {
            GameInformation selectedGame = gamesList.getSelectedValue();
            if (selectedGame != null) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                try {
                    selectedGame.getFactory().get().launch(frame);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return playButton;
    }
}