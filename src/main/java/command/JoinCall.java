package command;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.bandcamp.BandcampAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.soundcloud.SoundCloudAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import music.TrackScheduler;
import music.AudioPlayerSendHandler;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.managers.AudioManager;

import java.util.HashMap;
import java.util.regex.Matcher;

public class JoinCall extends ListenerAdapter {

    public static HashMap<String, AudioManager> managers = new HashMap<>();
    public static AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
    public static AudioPlayer player = playerManager.createPlayer();
    public static TrackScheduler trackScheduler = new TrackScheduler(player);

    public static void join(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher joinCallMatcher, User author) {

        VoiceChannel voiceChannel = null;
        ChannelType type = channel.getType();
        switch (type) {

            case GROUP:
                voiceChannel = (VoiceChannel) event.getGroup().getCurrentCall().getCallableChannel();
                break;

            case PRIVATE:
                event.getPrivateChannel().startCall();
                voiceChannel = (VoiceChannel) event.getPrivateChannel().getCurrentCall().getCallableChannel();
                break;

            case TEXT:
                for (VoiceChannel vc : event.getGuild().getVoiceChannels()) {
                    for (Member member : vc.getMembers()) {
                        if (author.getDiscriminator().equalsIgnoreCase(member.getUser().getDiscriminator())) {
                            voiceChannel = vc;
                            break;
                        }
                    }
                    if (voiceChannel != null && voiceChannel.equals(vc)) {
                        break;
                    }
                }
                break;
        }
        try {
            managers.put(voiceChannel.getId(), (AudioManager) voiceChannel.getManager());
        } catch (NullPointerException e) {
            channel.sendMessage("Nope don't feel like it.").complete();
        }

        player.addListener(trackScheduler);

        managers.get(voiceChannel.getId()).setSendingHandler(new AudioPlayerSendHandler(player));
        managers.get(voiceChannel.getId()).openAudioConnection(voiceChannel);
    }

    public static void leave(MessageReceivedEvent event, MessageChannel channel, String msg, Matcher joinCallMatcher, User author) {
        VoiceChannel voiceChannel = null;
        switch (channel.getType()) {
            case GROUP:
                voiceChannel = (VoiceChannel) event.getGroup().getCurrentCall().getCallableChannel();
                break;
            case PRIVATE:
                voiceChannel = (VoiceChannel) event.getPrivateChannel().getCurrentCall().getCallableChannel();
                break;
            case TEXT:
                for (VoiceChannel vc : event.getGuild().getVoiceChannels()) {
                    for (Member member : vc.getMembers()) {
                        if (author.getDiscriminator().equalsIgnoreCase(member.getUser().getDiscriminator())) {
                            voiceChannel = vc;
                            break;
                        }
                    }
                    if (voiceChannel != null && voiceChannel.equals(vc)) {
                        break;
                    }
                }
                break;
        }
        managers.get(voiceChannel.getId()).closeAudioConnection();
    }
}
