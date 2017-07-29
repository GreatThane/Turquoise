package enums;

/**
 * Created by ethan on 4/15/17.
 */
public enum Game {

    MEGA_WALLS("Mega Walls"),
    BLITZ_SURVIVAL("Blitz Survival Games"),
    COPS_AND_CRIMS("Cops and Crims");

    private String gameString;

    Game(String gameString) {
        this.gameString = gameString;
    }

    public String getGameString() {
        return gameString;
    }

}
