package com.playsafe.console.roulette.util;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.playsafe.console.roulette.model.PlayerBet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Optional;

@Component
public class PlayerBetsConsole implements Iterator<PlayerBet>, DisposableBean {

    private final PlayerBetHandler playerBetHandler;

    private final BufferedReader betsBufferedReader;

    @Autowired
    public PlayerBetsConsole(PlayerBetHandler playerBetHandler) {
        this(new BufferedReader(new InputStreamReader(System.in)), playerBetHandler);
    }

    private PlayerBetsConsole(BufferedReader bufferedReader, PlayerBetHandler playerBetHandler) {
        this.betsBufferedReader = bufferedReader;
        this.playerBetHandler = playerBetHandler;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public PlayerBet next() {
        try {
            String input = betsBufferedReader.readLine();
            Optional<PlayerBet> playerBet = playerBetHandler.createPlayerBet(input);
            return playerBet.orElseGet(this::next);
        } catch (IOException | NumberFormatException e) {
            return next();
        }
    }

    @Override
    public void destroy() throws Exception {
        betsBufferedReader.close();
    }
}
