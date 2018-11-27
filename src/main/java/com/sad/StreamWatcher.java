package com.sad;

import me.philippheuer.twitch4j.TwitchClient;
import me.philippheuer.twitch4j.endpoints.StreamEndpoint;
import me.philippheuer.twitch4j.endpoints.UserEndpoint;
import me.philippheuer.twitch4j.model.Stream;
import me.philippheuer.twitch4j.model.User;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.util.Optional;
import java.util.Random;

public class StreamWatcher extends Thread {
    private JDA jda;
    private TwitchClient twitchClient;
    private Config config = new Config();

    private Long twitchChannelID = 0L;
    private int delay = 30000;
    private String lastStatus = "offline";
    Long channelToNotify = config.getChannelToNotify();
    Random rand = new Random();

    StreamWatcher(JDA jda, TwitchClient twitchClient) {
        this.jda = jda;
        this.twitchClient = twitchClient;
        String channelToWatch = config.getTwitchCannelName();
        twitchChannelID = twitchClient.getUserEndpoint().getUserIdByUserName(channelToWatch);
    }

    @Override
    public synchronized void run() {
        StreamEndpoint streamEndpoint;
        Optional<Stream> stream;

        for (; ; ) {
            streamEndpoint = twitchClient.getStreamEndpoint();
            stream = Optional.ofNullable(streamEndpoint.getByChannel(twitchClient
                    .getChannelEndpoint()
                    .getChannel(twitchChannelID)));
            if (stream.isPresent() && "offline".equals(lastStatus)) {
                lastStatus = "online";
                jda.getTextChannelById(channelToNotify).sendMessage("@everyone https://www.twitch.tv/vkusnaypisechka").queue();
            } else if(!stream.isPresent() && "online".equals(lastStatus)){
                lastStatus = "offline";
            }

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
