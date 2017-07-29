package command;

import enums.Game;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.GameMatcher;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by ethan on 4/14/17.
 */
public class StatsFinder {

    public static void showStats(MessageReceivedEvent event, PrivateChannel privateChannel, String msg, Matcher statsMatcher) {

        String player = statsMatcher.group(1);
        String gameInput = statsMatcher.group(2);
        Game game;
        try {
            game = GameMatcher.getGame(gameInput);
        } catch (Exception e) {
            privateChannel.sendMessage("Sorry, I don't know how to find the game '" + gameInput + "'").complete();
            return;
        }

        privateChannel.sendMessage("Oh, so you want to see stats for " + player + " in the game " + game.getGameString() + "?").complete();

        try {
            URL playerUrl = new URL("http://plancke.io/hypixel/player/stats/" + player);
            HttpURLConnection connection = (HttpURLConnection) playerUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml");
            connection.setRequestProperty("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/57.0.2987.98 Chrome/57.0.2987.98 Safari/537.36");;

            InputStream stream = (InputStream)connection.getContent();
            String html = new BufferedReader(new InputStreamReader(stream))
                    .lines().collect(Collectors.joining("\n"));
            connection.disconnect();
            Pattern listItems = Pattern.compile(game.getGameString() + " <\\/a>.*?<ul.*?>(.*?)<\\/ul>", Pattern.DOTALL);
            Matcher gameMatch = listItems.matcher(html);
            if(!gameMatch.matches()) {
                privateChannel.sendMessage("Unable to find the game stats for " + game.getGameString()).complete();
                return;
            }

            String statsList = gameMatch.group(1);
            System.out.println(statsList);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
