package org.example.mainmenu;

import org.example.engine.Game;
import java.io.*;
import java.util.function.Supplier;

public class GameInformation {
    String title;
    String description;
    Supplier<Game> factory;

    public GameInformation(String title, String description, Supplier<Game> factory) {
        this.title = title;
        this.description = description;
        this.factory = factory;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Supplier<Game> getFactory() { return factory; }

    public static String getContents(String fileLocation) throws IOException {
        StringBuilder contents = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileLocation))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) { contents.append(line).append("\n"); }
        }
        return contents.toString();
    }

    @Override public String toString() { return title; }
}