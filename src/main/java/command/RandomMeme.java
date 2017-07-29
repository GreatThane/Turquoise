package command;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;
import java.util.regex.Matcher;

public class RandomMeme {

    public static void showRandomMeme(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher randomVideoMatcher, User author) {

        Random r = new Random();
        int Low = 1;
        int High = 10;
        int Result = r.nextInt(High-Low) + Low;

        String url = "oops I broke!";
        int dankness = Result%10;
        switch (Result) {

            case 1: url = "http://i.imgur.com/0A0gEYd.gifv";
                break;

            case 2: url = "http://i.imgur.com/gR5nsrx.gifv";
                break;

            case 3: url = "http://i.imgur.com/W6L1yMp.gifv";
                break;

            case 4: url = "http://i.imgur.com/ItRZTef.gifv";
                break;

            case 5: url = "https://i.imgur.com/ujOtU7a.gifv";
                break;

            case 6: url = "https://i.imgur.com/K1A4ImX.gifv";
                break;

            case 7: url = "https://i.imgur.com/7VQOaA5.gifv";
                break;

            case 8: url = "http://i.imgur.com/a4zUM14.gifv";
                break;

            case 9: url = "http://i.imgur.com/DI88U68.gifv";
                break;

            case 10: url = "http://i.imgur.com/423SL9e.gifv";
                break;
        }
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle("**Dankness Level:** *" + dankness + "*");
        embedBuilder.setImage(url);
        channel.sendMessage(embedBuilder.build()).complete();
    }
}
