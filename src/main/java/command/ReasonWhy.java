package command;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;
import java.util.regex.Matcher;

/**
 * Created by ethan on 4/16/17.
 */
public class ReasonWhy {

    public static void showReason(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher reasonMatcher, User author) {

        if (author.getDiscriminator().equals("2948")||author.getDiscriminator().equals("5993")) {

            Random r = new Random();
            int Low = 1;
            int High = 18;
            int Result = r.nextInt(High-Low) + Low;

            String reason = "O jeez something messed up";

            switch (Result) {
                case 1:
                    reason = "He loves you for your beautiful eyes.";
                    break;
                case 2:
                    reason = "He loves you for your long, flowing black hair.";
                    break;
                case 3:
                    reason = "He loves you for incredible intelligence.";
                    break;
                case 4:
                    reason = "He loves you for your cute little uniform.";
                    break;
                case 5:
                    reason = "He loves you for your selflessness.";
                    break;
                case 6:
                    reason = "He loves you for your petite size.";
                    break;
                case 7:
                    reason = "He loves you for the smile you always try and hide.";
                    break;
                case 8:
                    reason = "He loves you for your blushes, even the ones in public.";
                    break;
                case 9:
                    reason = "He loves you for your little flirts, no matter how subtle.";
                    break;
                case 10:
                    reason = "He loves you for who you are, and no matter who you choose to become.";
                    break;
                case 11:
                    reason = "He loves you for your surprises, even if he's afraid of them at first.";
                    break;
                case 12:
                    reason = "He loves you for all the things you hate about yourself.";
                    break;
                case 13:
                    reason = "He loves you for the tiny little sounds you make.";
                    break;
                case 14:
                    reason = "He loves you for everything you're willing to do not only for him, but others.";
                    break;
                case 15:
                    reason = "He loves you for all the curled up positions you sit in.";
                    break;
                case 16:
                    reason = "\"nnnn\"";
                    break;
                case 17:
                    reason = "He loves you because every new thing he learns about you is another reason to love you.";
                    break;
            }
            channel.sendMessage("*Reason* **#" + Result + "** \n" + reason).complete();
        } else {
            return;
        }
    }
}
