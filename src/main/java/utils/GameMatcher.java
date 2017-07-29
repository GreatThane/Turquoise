package utils;

import enums.Game;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ethan on 4/15/17.
 */
public class GameMatcher {

    private static final Map<String, Game> gameStrings = new HashMap<String, Game>() {{
       put("mega walls", Game.MEGA_WALLS);
       put("megawalls", Game.MEGA_WALLS);
       put("blitz survival", Game.BLITZ_SURVIVAL);
       put("blitzsurvival", Game.BLITZ_SURVIVAL);
       put("cvc", Game.COPS_AND_CRIMS);
       put("cops and crims", Game.COPS_AND_CRIMS);
       put("cops n crims", Game.COPS_AND_CRIMS);
    }};

    public static Game getGame(String gameName) {

        if(gameStrings.containsKey(gameName.toLowerCase())) {
            // found a match, return the game
            return gameStrings.get(gameName.toLowerCase());
        } else {
            throw new RuntimeException("Game Not Found");
        }

    }
}
