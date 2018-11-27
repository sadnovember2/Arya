package com.sad;

import me.philippheuer.twitch4j.events.EventSubscriber;
import me.philippheuer.twitch4j.events.event.irc.ChannelMessageEvent;

public class TwitchChatWatcher {

    @EventSubscriber
    public void onChannelMessage(ChannelMessageEvent event) {
        //System.out.println("Channel [" +event.getChannel().getDisplayName() + "] - User[" + event.getUser().getDisplayName() + "] - Message [" + event.getMessage() + "]");
        String message = event.getMessage().toLowerCase();
        if (message.equals("ку") ||
                message.contains("прив") ||
                message.contains("привет") ||
                message.contains("дарова") ||
                message.contains("здрасти") ||
                message.contains("хай") ||
                message.contains("qq") ||
                message.contains("hallo") ||
                message.contains("hi") ||
                message.contains("laba diena") ||
                message.contains("labdien") ||
                message.contains("labvakar") ||
                message.contains("cau")) {
            sendMessageToChannel(event, event.getChannel().getName(), "Добро пожаловать " + event.getUser().getName());
        } else if (message.contains("добрый вечер") ||
                message.contains("добрый день")){
            sendMessageToChannel(event, event.getChannel().getName(), "Добрый!!1");
        } else if (message.contains("пока") ||
                message.contains("до свидания") ||
                message.equals("ата") ||
                message.contains("ушел") ||
                message.contains("ухожу")){
            sendMessageToChannel(event, event.getChannel().getName(), "До встречи "  + event.getUser().getName() + "!");
        } else if (message.contains("прощай")){
            sendMessageToChannel(event, event.getChannel().getName(), "Ой, да кому ты нужен, бака!");
        }
    }

    public void sendMessageToChannel(ChannelMessageEvent event ,String channelName, String message) {
        event.getClient().getMessageInterface().sendMessage(channelName, message);
    }
}