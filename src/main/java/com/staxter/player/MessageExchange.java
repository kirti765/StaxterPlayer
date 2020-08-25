package com.staxter.player;

import com.staxter.exception.PlayerException;
import com.staxter.PlayerMessages;

import java.util.Queue;
import java.util.function.Function;

public class MessageExchange implements Player<PlayerMessages>{

    private final String name;
    private final Function<PlayerMessages, PlayerMessages> messageBuilder;

    private int sentMessages;
    private int receivedMessages;

    public MessageExchange(String Pname, Function<PlayerMessages, PlayerMessages> messageBuilder) {
        this.name = Pname;
        if (messageBuilder == null) {
            throw new PlayerException("Cannot create player because message builder should not be null!");
        }
        this.messageBuilder = messageBuilder;
    }

    @Override
    public void send(Queue<PlayerMessages> writeQueue, PlayerMessages message) {
        if(writeQueue==null){
            throw new PlayerException("Cannot send a message if the message is null!");
        }
        if (message == null) {
            throw new PlayerException("Cannot send a message if the message is null!");
        }
        PlayerMessages newMessage = this.messageBuilder.apply(message);

        System.out.println(name + " sent message " + newMessage.toString());
        writeQueue.add(newMessage);
        this.sentMessages++;
    }

    public PlayerMessages receive(Queue<PlayerMessages> readQueue) {
        if (readQueue == null) {
            throw new PlayerException("Cannot receive a message if the queue is null!");
        }
        PlayerMessages message = readQueue.poll();
        if (message == null) {
            throw new PlayerException("Cannot receive a message! Queue is empty");
        }

        this.receivedMessages++;
        System.out.println(name + " received message " + message.toString());
        return message;
    }
    public int getSentMessages() {
        return sentMessages;
    }

    public int getReceivedMessages() {
        return receivedMessages;
    }
}
