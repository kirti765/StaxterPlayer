package com.staxter;

import com.staxter.communication.PlayerCommunication;
import com.staxter.util.constant.Constant;

import java.io.IOException;

/**
 * Main class to start the application.
 */
public class PlayerMain {

    public static void main(String[] args) {
        PlayerCommunication s = new PlayerCommunication(Constant.messageCount);
        s.initiateMessaging();


    }
}
