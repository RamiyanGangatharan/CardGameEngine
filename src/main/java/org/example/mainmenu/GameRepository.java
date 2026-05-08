package org.example.mainmenu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameRepository {

    /**
     * This method is in charge of the display of the games available to the user.
     * @return a list of games with metadata
     * @throws IOException obligatory file exception handling
     */
    public static List<GameInformation> loadGames() throws IOException {

        List<GameInformation> games = new ArrayList<>();

        games.add(
            new GameInformation(
                "BlackJack",
                GameInformation.getContents("src/main/resources/blackjackdesc.txt")
            )
        );

        games.add(
            new GameInformation(
                    "Poker",
                    GameInformation.getContents("src/main/resources/pokerdesc.txt")
            )
        );

        return games;
    }
}