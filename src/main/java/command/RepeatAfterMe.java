package command;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.regex.Matcher;

/**
 * Created by ethan on 7/1/17.
 */
public class RepeatAfterMe {

    public static void showRepeatAfterMe(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher repeatAfterMeMatcher, User author) {

        String copyMessage = repeatAfterMeMatcher.group(1);
        event.getMessage().delete().reason(author.getName() + "is having me repeat them.").complete();
        channel.sendMessage(copyMessage).complete();
    }
}
