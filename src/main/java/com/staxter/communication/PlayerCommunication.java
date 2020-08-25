package com.staxter.communication;

import com.staxter.PlayerMessages;
import com.staxter.player.MessageExchange;
import com.staxter.exception.PlayerException;
import com.staxter.util.constant.Constant;

import java.util.LinkedList;
import java.util.Optional;

public class PlayerCommunication {

    private final int msgCount;

    public PlayerCommunication(int msgCount) {
        this.msgCount = msgCount;
    }

    public void initiateMessaging() {
        MessageExchange Initiator = new MessageExchange(Constant.Initiator, this::createMessage);
        MessageExchange Consumer = new MessageExchange(Constant.Consumer, this::createMessage);

        PlayerMessages message = new PlayerMessages(1, "Hey !!");

        /*first player sends messages to this queue and the second player reads from it*/
        LinkedList<PlayerMessages> firstQueue = new LinkedList<>();

        /*second player sends messages to this queue and the first player reads from it*/
        LinkedList<PlayerMessages> secondQueue = new LinkedList<>();

        while (Initiator.getSentMessages() != msgCount && Initiator.getReceivedMessages() != msgCount) {

            Initiator.send(firstQueue, message);
            message = Consumer.receive(firstQueue);
            Consumer.send(secondQueue, message);
            message = Initiator.receive(secondQueue);
        }

    }

    protected PlayerMessages createMessage(PlayerMessages message) {
        return Optional.ofNullable(message)
                .map(msg -> {
                            String messageText = msg.getMessage() == null ? "" : msg.getMessage();
                            return new PlayerMessages(msg.getCounter() + 1,messageText + " " + msg.getCounter()
                            );
                        }
                )
                .orElseThrow(() -> new PlayerException("Cannot build messages are null!"));
    }
}
