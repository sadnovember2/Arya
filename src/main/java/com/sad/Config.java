package com.sad;

class Config {
    String getDiscordKey() {
        return discordKey;
    }
    String getTwitchClientID() {
        return twitchClientID;
    }
    String getTwitchClientSecret() {
        return twitchClientSecret;
    }
    String getTwitchCredential() {
        return twitchCredential;
    }
    String getTwitchCannelName() {
        return twitchCannelName;
    }
    Long getChannelToNotify() {
        return channelToNotify;
    }

    //DISCORD AUTH SECTION
    private String discordKey = "";

    //TWITCH AUTH SECTION
    private String twitchClientID = "";
    private String twitchClientSecret = "";
    private String twitchCredential = "";

    //TWITCH DATA SECTION
    private String twitchCannelName = "";

    //DISCORD DATA SECTION
    private Long channelToNotify = 352077573565448192L;
}