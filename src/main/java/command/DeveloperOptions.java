package command;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ethan on 6/10/17.
 */
public class DeveloperOptions {

    private static final Pattern userIdOf = Pattern.compile("UserId of @(.*?)#(.*)", Pattern.CASE_INSENSITIVE);
    private static List<String> admins = Arrays.asList("2948");

    public static void showDeveloperOptions(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher developerMatcher, User author) {

        if (admins.contains(author.getDiscriminator())) {

            String command = developerMatcher.group(2);

            Matcher userIdMatcher = userIdOf.matcher(command);
            if (userIdMatcher.matches()) {
                String ID = userIdMatcher.group(2);
                String user = userIdMatcher.group(1);

                channel.sendMessage("User: " + user + " " + "ID: " + ID).complete();
            }
        }

    }
}
