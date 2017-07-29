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
            int memberCount = event.getMessage().getMentionedUsers().size();

            for (int current = 0; current <= memberCount; current++) {

                int term = 1;

                int hotness = 0;
                int cuteness = 0;
                int sexiness = 0;

                while (term <= 3) {

                    Random r = new Random();
                    int Low = 1;
                    int High = 15;
                    int result = r.nextInt(High - Low) + Low;

                    if (term == 1) {
                        hotness = result;
                    } else if (term == 2) {
                        cuteness = result;
                    } else if (term == 3) {
                        sexiness = result;
                    }
                    term++;
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
                User target = event.getMessage().getMentionedUsers().get(current);
                embedBuilder.setAuthor(target.getName(), target.getAvatarUrl(), target.getAvatarUrl());
                embedBuilder.addField("Hotness", Integer.toString(hotness), true);
                embedBuilder.addField("Cuteness", Integer.toString(cuteness), true);
                embedBuilder.addField("Sexiness", Integer.toString(sexiness), true);
                embedBuilder.setColor(color);
                channel.sendMessage(embedBuilder.build()).complete();
            }
        } else {

            int term = 1;

            int hotness = 0;
            int cuteness = 0;
            int sexiness = 0;

            while (term <= 3) {

                Random r = new Random();
                int Low = 1;
                int High = 15;
                int result = r.nextInt(High - Low) + Low;

                if (term == 1) {
                    hotness = result;
                } else if (term == 2) {
                    cuteness = result;
                } else if (term == 3) {
                    sexiness = result;
                }
                term++;
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
            embedBuilder.setAuthor(author.getName(), author.getAvatarUrl(), author.getAvatarUrl());
            embedBuilder.addField("Hotness", Integer.toString(hotness), true);
            embedBuilder.addField("Cuteness", Integer.toString(cuteness), true);
            embedBuilder.addField("Sexiness", Integer.toString(sexiness), true);
            embedBuilder.setColor(color);
            channel.sendMessage(embedBuilder.build()).complete();
        }
    }
}