package command;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;
import java.util.regex.Matcher;

public class RandomVideo {

    public static void showRandomVideo(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher randomVideoMatcher, User author) {

        Random r = new Random();
        int Low = 1;
        int High = 40;
        int Result = r.nextInt(High-Low) + Low;

        String url = "oops I broke!";
        switch (Result) {

            case 1: url = "https://www.youtube.com/watch?v=q6EoRBvdVPQ";
                break;

            case 2: url = "https://www.youtube.com/watch?v=8YWl7tDGUPA";
                break;

            case 3: url = "https://www.youtube.com/watch?v=6bnanI9jXps";
                break;

            case 4: url = "https://www.youtube.com/watch?v=SBeYzoQPbu8";
                break;

            case 5: url = "https://www.youtube.com/watch?v=ixQkcuZhXg8";
                break;

            case 6: url = "https://www.youtube.com/watch?v=EWF8Nfm-LLk";
                break;

            case 7: url = "https://www.youtube.com/watch?v=v3i8vsIUA7Q";
                break;

            case 8: url = "https://www.youtube.com/watch?v=zMtj4yJKuzk";
                break;

            case 9: url = "https://www.youtube.com/watch?v=GzgavGowD_A";
                break;

            case 10: url = "https://www.youtube.com/watch?v=5lL5xRKquPQ";
                break;

            case 11: url = "https://www.youtube.com/watch?v=PaFnO5LKTSs";
                break;

            case 12: url = "https://www.youtube.com/watch?v=MwBxDnL4PZE";
                break;

            case 13: url = "https://www.youtube.com/watch?v=rtChPB6NjJY";
                break;

            case 14: url = "https://www.youtube.com/watch?v=6s2iwMFZk_Q";
                break;

            case 15: url = "https://www.youtube.com/watch?v=AZdgxWV4SgU";
                break;

            case 16: url = "https://www.youtube.com/watch?v=a4uFJMNSthE";
                break;

            case 17: url = "https://www.youtube.com/watch?v=AZYErjxUqyE";
                break;

            case 18: url = "https://www.youtube.com/watch?v=3j9XNhrwVtY";
                break;

            case 19: url = "https://www.youtube.com/watch?v=yr_Rpk9HR1g";
                break;

            case 20: url = "https://www.youtube.com/watch?v=YAg-WauGrLU";
                break;

            case 21: url = "https://www.youtube.com/watch?v=8o6SeDeGnSQ";
                break;

            case 22: url = "https://www.youtube.com/watch?v=KaeYczuhDqw";
                break;

            case 23: url = "https://www.youtube.com/watch?v=lg_sd_EpTDs";
                break;

            case 24: url = "https://www.youtube.com/watch?v=yNtySt6Fg30";
                break;

            case 25: url = "https://www.youtube.com/watch?v=XJZgHAyPq6I";
                break;

            case 26: url = "https://www.youtube.com/watch?v=CIdXPIN3j38";
                break;

            case 27: url = "https://www.youtube.com/watch?v=sz2mmM-kN1I";
                break;

            case 28: url = "https://www.youtube.com/watch?v=uGQ9Hlw1DfM";
                break;

            case 29: url = "https://www.youtube.com/watch?v=xvQUiX26RfE";
                break;

            case 30: url = "https://www.youtube.com/watch?v=bEgeh5hA5ko";
                break;

            case 31: url = "https://www.youtube.com/watch?v=Z0ohcDZ4bQA";
                break;

            case 32: url = "https://www.youtube.com/watch?v=aRsWk4JZa5k";
                break;

            case 33: url = "https://www.youtube.com/watch?v=s8ssV6FAA5M";
                break;

            case 34: url = "https://www.youtube.com/watch?v=Qf0kC-MJZrU";
                break;

            case 35: url = "https://www.youtube.com/watch?v=lRkUQShOHnQ";
                break;

            case 36: url = "https://www.youtube.com/watch?v=WNmCm4oyrkY";
                break;

            case 37: url = "https://www.youtube.com/watch?v=oeb5LdAyLC8";
                break;

            case 38: url = "https://www.youtube.com/watch?v=LZgeIReY04c";
                break;

            case 39: url = "https://www.youtube.com/watch?v=eDxa2tmbhSg";
                break;

            case 40: url = "https://www.youtube.com/watch?v=7bMS4jLp0O4";
                break;


        }

        channel.sendMessage("*Here ya go!*\n" + url).complete();

    }
}
