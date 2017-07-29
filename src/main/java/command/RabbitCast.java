package command;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RabbitCast {

    public static void showRabbitCast(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher rateMeMatcherMatcher, User author) {

        Pattern commandPattern = Pattern.compile("(?:.*?)\\shttps?://(.*?)\\sabout\\s(.*)", Pattern.CASE_INSENSITIVE);
        Matcher commandMatcher = commandPattern.matcher(msg);
        final boolean matches = commandMatcher.matches();
        if (matches) {
            String url = "https://" + commandMatcher.group(1);
            String validUrl = "rabb.it";

            if (url.toLowerCase().contains(validUrl.toLowerCase())) {

                event.getMessage().delete().reason("RabbitCast").complete();
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setAuthor(author.getName(), author.getAvatarUrl(), author.getAvatarUrl());
                embedBuilder.setThumbnail("https://p6.zdassets.com/hc/settings_assets/752668/200175077/lranaNjxiaHxUZ2y4qX0Yg-For_Typeform.png");
                embedBuilder.setTitle(author.getName() + "\'s RabbitCast", url);
                embedBuilder.addField("About:", commandMatcher.group(2), false);
                embedBuilder.setColor(Color.GRAY);
                embedBuilder.setDescription(author.getName() + "\'s made a RabbitCast! Using this service you can watch a video together live. Click the link above to join.");
                channel.sendMessage(embedBuilder.build()).complete();

            } else {
                channel.sendMessage(author.getAsMention() + ": That is not a valid rabbit url. Try again with a link that contains \"rabb.it\"").complete();
            }
        }
    }
}
