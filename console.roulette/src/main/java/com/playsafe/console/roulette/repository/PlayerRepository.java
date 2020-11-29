package com.playsafe.console.roulette.repository;

import java.util.Set;

import com.playsafe.console.roulette.model.Player;

public interface PlayerRepository {
    void addPlayer(Player player);

    Set<Player> getRegisteredPlayers();
}
