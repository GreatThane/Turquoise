package command;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Random;
import java.util.regex.Matcher;

public class RateMe {

    public static void showRateMe(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher rateMeMatcherMatcher, User author) {

        if (!event.getMessage().getMentionedUsers().isEmpty()) {

            for (User target : event.getMessage().getMentionedUsers()) {
                rateUser(target, channel);
            }
        } else {
            rateUser(author, channel);
        }
    }

    private static void rateUser(User target, MessageChannel channel) {
        int hotness = 0;
        int cuteness = 0;
        int sexiness = 0;

        for (int term = 1; term <= 3; term++) {
            Random r = new Random();
            int low = 1;
            int high = 15;
            int result = r.nextInt(high - low) + low;

            switch (term) {
                case 1:
                    hotness = result;
                    break;
                case 2:
                    cuteness = result;
                    break;
                case 3:
                    sexiness = result;
                    break;
                default:
                    //should never get here
            }
        }
        Color color = Color.LIGHT_GRAY;
        if (hotness + cuteness + sexiness > 28) {
            color = Color.RED;

        } else if (hotness + cuteness + sexiness > 14 && hotness + cuteness + sexiness <= 28) {
            color = Color.PINK;

        } else if (hotness + cuteness + sexiness <= 14) {
            color = Color.LIGHT_GRAY;
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(target.getName(), target.getAvatarUrl(), target.getAvatarUrl());
        embedBuilder.addField("Hotness", Integer.toString(hotness), true);
        embedBuilder.addField("Cuteness", Integer.toString(cuteness), true);
        embedBuilder.addField("Sexiness", Integer.toString(sexiness), true);
        embedBuilder.setColor(color);
        channel.sendMessage(embedBuilder.build()).complete();
    }

}