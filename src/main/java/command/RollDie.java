package command;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;
import java.util.regex.Matcher;

/**
 * Created by ethan on 7/2/17.
 */
public class RollDie {

    public static void showRollDie(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher repeatAfterMeMatcher, User author) {

        Random r = new Random();
        int Low = 1;
        int High = 7;
        int Result = r.nextInt(High-Low) + Low;

        if (Result == 1) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setImage("http://i.imgur.com/CZQkstK.png");
            channel.sendMessage(embedBuilder.build()).complete();
        }
        else if (Result == 2) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setImage("http://i.imgur.com/xYmBbGk.png");
            channel.sendMessage(embedBuilder.build()).complete();
        }
        else if (Result == 3) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setImage("http://i.imgur.com/Cea620S.png");
            channel.sendMessage(embedBuilder.build()).complete();
        }
        else if (Result == 4) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setImage("http://i.imgur.com/lJ0PHny.png");
            channel.sendMessage(embedBuilder.build()).complete();
        }
        else if (Result == 5) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setImage("http://i.imgur.com/poNW5Jl.png");
            channel.sendMessage(embedBuilder.build()).complete();
        }
        else if (Result == 6) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setImage("http://i.imgur.com/Anz2rjM.png");
            channel.sendMessage(embedBuilder.build()).complete();
        }
    }
}
