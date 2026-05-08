package org.example.mainmenu;

import java.io.*;

public class GameInformation {
    String title;
    String description;

    /**
     * This is the primary dataset to hold information about a game.
     * @param title title of the game
     * @param description what the game is
     */
    public GameInformation(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // TODO: make a function that takes in a text file, reads its contents, then returns a string of its data.
    // https://coderanch.com/t/633713/java/Read-text-file-display-Pane
    public static String getContents(String fileLocation) throws IOException {
        StringBuilder contents = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) { contents.append(line).append("\n"); }
        }

        return contents.toString();
    }

    @Override public String toString() {return title;}
}
