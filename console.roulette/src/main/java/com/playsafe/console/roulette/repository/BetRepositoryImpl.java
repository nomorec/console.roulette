package com.playsafe.console.roulette.repository;

import org.springframework.stereotype.Service;

import com.playsafe.console.roulette.model.PlayerBet;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class BetRepositoryImpl implements BetRepository {
    private final AtomicReference<Set<PlayerBet>> playerBetsHolder = new AtomicReference<>(emptySet());

    @Override
    public void addBet(PlayerBet playerBet) {
        playerBetsHolder.updateAndGet(bets -> {
            bets.add(playerBet);
            return bets;
        });
    }

    @Override
    public Set<PlayerBet> getCurrentGameBets() {
        return playerBetsHolder.getAndSet(emptySet());
    }

    private ConcurrentHashMap.KeySetView<PlayerBet, Boolean> emptySet() {
        return ConcurrentHashMap.newKeySet();
    }
}
