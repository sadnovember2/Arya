package com.sad;

import me.philippheuer.twitch4j.TwitchClient;
import me.philippheuer.twitch4j.TwitchClientBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

public class Bot extends Thread {
    private Config config;
    private JDA jda;
    private TwitchClient twitchClient;


    public Bot() throws LoginException {
        config = new Config();
        jda = new JDABuilder(config.getDiscordKey())
                .addEventListener(new Cmd())
                .build();
        twitchClient = initTwitch();
        twitchClient.getDispatcher().registerListener(new TwitchChatWatcher());
    }

    @Override
    public void run() {
        twitchClient.getMessageInterface().joinChannel("vkusnaypisechka");
        StreamWatcher streamWatcher = new StreamWatcher(jda, twitchClient);
        streamWatcher.run();
    }

    private TwitchClient initTwitch(){
        return TwitchClientBuilder.init()
                .withClientId(config.getTwitchClientID())
                .withClientSecret(config.getTwitchClientSecret())
                .withCredential(config.getTwitchCredential())
                .connect();
    }
}