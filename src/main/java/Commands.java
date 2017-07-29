import command.*;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Thane on 4/4/17.
 */
public class Commands extends ListenerAdapter {

    private static final Pattern triggerPattern = Pattern.compile(".*?[\\s]*(Hey|Hi|Hello|Yo|Okay|Ok|Oi|Ay)[\\s]*Turq[,:;]?\\s(.*)\\?*", Pattern.CASE_INSENSITIVE);
    private static final Pattern statsPattern = Pattern.compile(".*stats for (.*?) in (.*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern reasonPattern = Pattern.compile("(.*?)reason why(.*?)", Pattern.CASE_INSENSITIVE);
    private static final Pattern calendarPattern = Pattern.compile("(.?)(?:where is|when is)\\s?(Thane|Bluee)*(.*?)\\??", Pattern.CASE_INSENSITIVE);
    private static final Pattern developerPattern = Pattern.compile("(.*)\\s?developer option\\s?[,:;\\-=~]?\\s?(.*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern sendNudesPattern = Pattern.compile(".*(send|have|give)\\s?.*?\\s?nude.*", Pattern.CASE_INSENSITIVE);
    private static final Pattern coinFlipPattern = Pattern.compile(".*?(?:flip|toss)\\s?.*?\\s?coin.*?", Pattern.CASE_INSENSITIVE);
    private static final Pattern repeatAfterMePattern = Pattern.compile(".*?repeat\\safter\\sme\\s?[,:;\\-=~]?\\s?\"?(.*?)\"?", Pattern.CASE_INSENSITIVE);
    private static final Pattern rollDiePattern = Pattern.compile(".*?(?:roll|throw|catch|spin)\\s?.*?\\s?(die|dice).*", Pattern.CASE_INSENSITIVE);
    private static final Pattern randomVideoPattern = Pattern.compile(".*?(vid|video|movie).*", Pattern.CASE_INSENSITIVE);
    private static final Pattern randomMemePattern = Pattern.compile(".*?meme.*", Pattern.CASE_INSENSITIVE);
    private static final Pattern rateMePattern = Pattern.compile(".*?(?:rate|r8)\\s(.*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern rabbitPattern = Pattern.compile(".*?(rabbit\\scast|rabbitcast|rabbit).*", Pattern.CASE_INSENSITIVE);
    private static final Pattern helpPattern = Pattern.compile(".*?(?:help|command).*", Pattern.CASE_INSENSITIVE);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        JDA jda = event.getJDA();
        User author = event.getAuthor();
        Message message = event.getMessage();
        MessageChannel channel = event.getChannel();

        String msg = message.getContent();

        boolean isBot = author.isBot();
        if (isBot) {
            return;
        }

        Matcher matcher = triggerPattern.matcher(msg);
        if (!matcher.matches()) {
            return;
        }

        String botCommand = matcher.group(2);

        // Public channel commands only

        if (event.isFromType(ChannelType.TEXT)) {

            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            Member member = event.getMember();
            String name = member.getEffectiveName();

            System.out.printf("(%s)[%s]<%s>: %s\n", guild.getName(), textChannel.getName(), name, msg);

            Matcher developerMatcher = developerPattern.matcher(botCommand);
            if (developerMatcher.matches()) {
                DeveloperOptions.showDeveloperOptions(event, textChannel, msg, developerMatcher, author);
                return;
            }
            Matcher rabbitMatcher = rabbitPattern.matcher(botCommand);
            if (rabbitMatcher.matches()) {
                RabbitCast.showRabbitCast(event, channel, event.getMessage().getContent(), rabbitMatcher, event.getAuthor());
                return;
            }

            if (processCommonCommands(botCommand, textChannel, event)) {
                return;
            }

            channel.sendMessage(event.getAuthor().getAsMention() + ": I don't understand what you're asking for").complete();

        } else if (event.isFromType(ChannelType.PRIVATE)) {

            // Private channel commands only

            PrivateChannel privateChannel = event.getPrivateChannel();
            System.out.printf("[PRIV]<%s>: %s\n", author.getName(), msg);

            Matcher statsMatcher = statsPattern.matcher(botCommand);
            if (statsMatcher.matches()) {
                StatsFinder.showStats(event, privateChannel, msg, statsMatcher);
                return;
            }
            Matcher calendarMatcher = calendarPattern.matcher(botCommand);
            if (calendarMatcher.matches()) {
                CalendarFinder.showCalendar(event, privateChannel, msg, calendarMatcher, author);
                return;
            }

            if (processCommonCommands(botCommand, privateChannel, event)) {
                return;
            }

            channel.sendMessage(event.getAuthor().getAsMention() + ", I don't understand what you're asking for").complete();

        }

    }

    //Both Public and Private channels commands

    private boolean processCommonCommands(String botCommand, MessageChannel channel, MessageReceivedEvent event) {

        Matcher reasonMatcher = reasonPattern.matcher(botCommand);
        if (reasonMatcher.matches()) {
            ReasonWhy.showReason(event, channel, event.getMessage().getContent(), reasonMatcher, event.getAuthor());
            return true;
        }

        Matcher nudesMatcher = sendNudesPattern.matcher(botCommand);
        if (nudesMatcher.matches()) {
            String nude = event.getAuthor().getAvatarUrl();
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setImage(nude);
            embedBuilder.setTitle("Here you go!");
            channel.sendMessage(embedBuilder.build()).complete();
            return true;
        }

        Matcher coinFlipMatcher = coinFlipPattern.matcher(botCommand);
        if (coinFlipMatcher.matches()) {
            CoinFlip.showCoinFlip(event, channel, event.getMessage().getContent(), coinFlipMatcher, event.getAuthor());
            return true;
        }

        Matcher repeatAfterMeMatcher = repeatAfterMePattern.matcher(botCommand);
        if (repeatAfterMeMatcher.matches()) {
            RepeatAfterMe.showRepeatAfterMe(event, channel, event.getMessage().getContent(), repeatAfterMeMatcher, event.getAuthor());
            return true;
        }

        Matcher rollDieMatcher = rollDiePattern.matcher(botCommand);
        if (rollDieMatcher.matches()) {
            RollDie.showRollDie(event, channel, event.getMessage().getContent(), rollDieMatcher, event.getAuthor());
            return true;
        }

        Matcher randomVideoMatcher = randomVideoPattern.matcher(botCommand);
        if (randomVideoMatcher.matches()) {
            RandomVideo.showRandomVideo(event, channel, event.getMessage().getContent(), randomVideoMatcher, event.getAuthor());
            return true;
        }

        Matcher randomMemeMatcher = randomMemePattern.matcher(botCommand);
        if (randomMemeMatcher.matches()) {
            RandomMeme.showRandomMeme(event, channel, event.getMessage().getContent(), randomMemeMatcher, event.getAuthor());
            return true;
        }

        Matcher rateMeMatcher = rateMePattern.matcher(botCommand);
        if (rateMeMatcher.matches()) {
            RateMe.showRateMe(event, channel, event.getMessage().getContent(), randomMemeMatcher, event.getAuthor());
            return true;
        }
        Matcher helpMatcher = helpPattern.matcher(botCommand);
        if (helpMatcher.matches()) {
            HelpCommand.showHelp(event, channel, event.getMessage().getContent(), randomMemeMatcher, event.getAuthor());
            return true;
        }

        return false;

    }


}
