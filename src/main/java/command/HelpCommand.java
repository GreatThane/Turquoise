package command;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.regex.Matcher;

public class HelpCommand {

    public static void showHelp(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher rateMeMatcherMatcher, User author) {

        channel.sendMessage("Right away. Here's a list of commands, " + author.getName() + ".\n```python\n#All commands are done by first typing out \"hey turq\" Or something of the sort.\n\"Command List\"\n" +
                "Send Nudes      ### Try it.\n" +
                "Flip a Coin     ### Makes a random 1 in 2 choice for you.\n" +
                "Roll a Die      ### Makes a random 1 in 6 choice for you.\n" +
                "Repeat After Me ### Makes Turq repeat exactly what you say. Don't be naughty.\n" +
                "Rabbit Cast     ### Makes a Rabbit Embed for you. Simply use the syntax \"Hey turq, Rabbitcast (link) about (subject)\"\n" +
                "Help!           ### Brings up this page... If you came here looking for this command you're a wizard.\n" +
                "```").complete();
    }
}
