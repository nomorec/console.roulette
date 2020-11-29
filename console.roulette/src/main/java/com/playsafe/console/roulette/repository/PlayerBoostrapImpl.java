package com.playsafe.console.roulette.repository;

import com.playsafe.console.roulette.model.Player;
import com.playsafe.console.roulette.util.PlayerHandler;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

@Service
public class PlayerBoostrapImpl implements PlayerBoostrap {

    private final PlayerHandler playerHandler;

    private final PlayerRepository playerRepository;

    public PlayerBoostrapImpl(PlayerHandler playerHandler, PlayerRepository playerRepository) {
        this.playerHandler = playerHandler;
        this.playerRepository = playerRepository;
    }

    @Override
    public void loadPlayers(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String input;
            while ((input = reader.readLine()) != null) {
                Optional<Player> player = playerHandler.createPlayer(input);
                player.ifPresent(playerRepository::addPlayer);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load players " + e.getMessage(), e);
        }
    }
}
