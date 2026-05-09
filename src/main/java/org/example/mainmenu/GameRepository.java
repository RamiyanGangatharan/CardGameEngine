package org.example.mainmenu;

import org.example.cardgames.war.warGame;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {
    public static List<GameInformation> loadGames() throws IOException {
        List<GameInformation> games = new ArrayList<>();
        games.add(new GameInformation(
                "War",
                GameInformation.getContents("src/main/resources/wardesc.txt"),
                warGame::new
        ));
        return games;
    }
}