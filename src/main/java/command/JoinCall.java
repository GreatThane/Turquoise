package command;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.hook.AudioOutputHook;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import com.sedmelluq.discord.lavaplayer.track.AudioReference;
import music.TrackScheduler;
import music.AudioPlayerSendHandler;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.VoiceChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.regex.Matcher;

public class JoinCall extends ListenerAdapter {

    public static void join(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher joinCallMatcher, User author) {
        Guild guild = event.getGuild();

        VoiceChannel voiceChannel = guild.getVoiceChannelsByName("general", true).get(0);
        AudioManager manager = guild.getAudioManager();

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        YoutubeAudioSourceManager ytManager = new YoutubeAudioSourceManager();
        playerManager.registerSourceManager(ytManager);
        //AudioSourceManagers.registerRemoteSources(playerManager);

        AudioPlayer player = playerManager.createPlayer();
        TrackScheduler trackScheduler = new TrackScheduler(player);
        player.addListener(trackScheduler);

        AudioTrackInfo audioTrackInfo = new AudioTrackInfo("theme song", "C418", 5, "Jmv5pTyz--I", false, "https://www.youtube.com/watch?v=Jmv5pTyz--I"); 
        YoutubeAudioTrack track = new YoutubeAudioTrack(audioTrackInfo, ytManager);

        trackScheduler.queue(track);
        
        //playerManager.loadItem

        manager.setSendingHandler(new AudioPlayerSendHandler(player));
        manager.openAudioConnection(voiceChannel);
    }
}
