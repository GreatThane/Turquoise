package command;

import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.regex.Matcher;

/**
 * Created by ethan on 6/10/17.
 */
public class CalendarFinder {

    public static void showCalendar(MessageReceivedEvent event, PrivateChannel privateChannel, String msg, Matcher calendarMatcher, User author) {
        if (author.getAsMention().substring(author.getAsMention().lastIndexOf("#")) == "#2948") {

        }
    }
}
