package command;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;
import java.util.regex.Matcher;

/**
 * Created by ethan on 7/1/17.
 */
public class CoinFlip {

    public static void showCoinFlip(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher coinFlipMatcher, User author) {

        Random r = new Random();
        int Low = 1;
        int High = 3;
        int Result = r.nextInt(High-Low) + Low;

        if (Result == 1) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setImage("http://i.imgur.com/5e4XEdP.png");
            channel.sendMessage(embedBuilder.build()).complete();
        }
        else if (Result == 2) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setImage("http://i.imgur.com/pzV2If3.png");
            channel.sendMessage(embedBuilder.build()).complete();
        }
    }
}
