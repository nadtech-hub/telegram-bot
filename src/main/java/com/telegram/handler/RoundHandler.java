package com.telegram.handler;

import com.telegram.model.Round;
import org.telegram.telegrambots.api.methods.send.SendMessage;

import java.util.*;

public class RoundHandler {

    private HashMap<Long, String> chatAndUserIds;
    private Round round;
    private final String LINE_SEPARATOR = System.lineSeparator();

    public RoundHandler( ) {
        this.chatAndUserIds = new HashMap<>();
        this.round = new Round();
    }

    public Round getRound() {
        return round;
    }

    public void addUser(Long id, String username) {
        chatAndUserIds.put(id, "@" + username);
    }

    public boolean isUserRegistered(Long key) {
        return chatAndUserIds.containsKey(key);
    }

    public Iterator<SendMessage> messageIterator() {
        final ArrayList<SendMessage> messages;messages = new ArrayList<>();

        for (Map.Entry<Long, String> id : chatAndUserIds.entrySet()) {
            SendMessage newMessage = new SendMessage().setChatId(id.getKey()).setText(createUsernamesList());
            messages.add(newMessage);
        }
        return messages.iterator();
    }

    private String createUsernamesList() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Long, String> entry : chatAndUserIds.entrySet()) {
            builder.append(entry.getValue());
            builder.append(LINE_SEPARATOR);
        }
        return builder.toString();
    }

    public HashMap<Long, String> getChatAndUserIds() {
        return chatAndUserIds;
    }
}
