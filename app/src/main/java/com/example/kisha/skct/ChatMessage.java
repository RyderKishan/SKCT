package com.example.kisha.skct;

/**
 * Created by kisha on 20-02-2017.
 */

import java.util.Date;

class ChatMessage
{

    String messageText, messageUser, profilePic;
    private long messageTime;

    ChatMessage() {}

    ChatMessage(String messageText, String messageUser, String profilePic)
    {
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.profilePic = profilePic;

        // Initialize to current time
        messageTime = new Date().getTime();
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}