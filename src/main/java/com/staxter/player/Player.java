package com.staxter.player;

import java.util.Queue;

public interface Player<TMessage> {

    /**
     * @param writeQueue A queue where the message should be sent
     * @param message A message
     */
    void send(Queue<TMessage> writeQueue, TMessage message);

    /**
     * @param readQueue A queue to read from
     * @return message A received message
     */
    TMessage receive(Queue<TMessage> readQueue);

}
