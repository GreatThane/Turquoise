package command;

import com.sedmelluq.discord.lavaplayer.source.AudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioTrack;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioTrack;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioTrack;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioTrack;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static command.JoinCall.playerManager;

public class PlayMusic {

    public static void play(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher matcher, User author) {

        YoutubeAudioSourceManager ytManager = new YoutubeAudioSourceManager();
        SoundCloudAudioSourceManager scManager = new SoundCloudAudioSourceManager();
        HttpAudioSourceManager httpManager = new HttpAudioSourceManager();
        BandcampAudioSourceManager bandcampManager = new BandcampAudioSourceManager();
        VimeoAudioSourceManager vimeoManager = new VimeoAudioSourceManager();
        LocalAudioSourceManager localManager = new LocalAudioSourceManager();
        TwitchStreamAudioSourceManager twitchManager = new TwitchStreamAudioSourceManager();
        playerManager.registerSourceManager(ytManager);
        playerManager.registerSourceManager(scManager);
        playerManager.registerSourceManager(httpManager);
        playerManager.registerSourceManager(bandcampManager);
        playerManager.registerSourceManager(vimeoManager);
        playerManager.registerSourceManager(localManager);
        playerManager.registerSourceManager(twitchManager);

        boolean stream = false;
        String url;
        if (matcher.group(1).startsWith("http") || matcher.group(1).startsWith("www")) {
            url = matcher.group(1);
        } else {
            return;
        }
        if (url.contains("twitch.")) {
            stream = true;
        }
        AudioTrackInfo info = new AudioTrackInfo("Song" ,author.getName(), 18, url.substring(20), stream, url);

        String platform;
        Pattern pattern = Pattern.compile(".*?(?:https?://)?(?:www\\.)?(.*?)(?:\\.)");
        Matcher urlMatcher = pattern.matcher(url);
        if (urlMatcher.matches()) {
            platform = urlMatcher.group(1);
        } else {
            channel.sendMessage(author.getAsMention() + ", that is currently an unsupported platform!").complete();
            return;
        }
        switch (platform.toLowerCase()) {
            case "youtube":
                YoutubeAudioTrack ytTrack = new YoutubeAudioTrack(info, ytManager);
                JoinCall.trackScheduler.queue(ytTrack);
                break;
            case "soundcloud":
                SoundCloudAudioTrack scTrack = new SoundCloudAudioTrack(info, scManager);
                JoinCall.trackScheduler.queue(scTrack);
                break;
            case "bandcamp":
                BandcampAudioTrack bandcampTrack = new BandcampAudioTrack(info, bandcampManager);
                JoinCall.trackScheduler.queue(bandcampTrack);
                break;
            case "vimeo":
                VimeoAudioTrack vimeoTrack = new VimeoAudioTrack(info, vimeoManager);
                JoinCall.trackScheduler.queue(vimeoTrack);
                break;
            case "twitch":
                TwitchStreamAudioTrack twitchTrack = new TwitchStreamAudioTrack(info, twitchManager);
                JoinCall.trackScheduler.queue(twitchTrack);
                break;
                default:
                    channel.sendMessage("Welp something broke, please inform dad of this error.").complete();
                    break;
        }

    }
}
