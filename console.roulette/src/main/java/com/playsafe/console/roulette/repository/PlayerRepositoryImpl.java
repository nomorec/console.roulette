package com.playsafe.console.roulette.repository;

import com.google.common.collect.ImmutableSet;
import com.playsafe.console.roulette.model.Player;

import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Service
public class PlayerRepositoryImpl implements PlayerRepository {

    private static final ReentrantReadWriteLock LOCK = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock READ_LOCK = LOCK.readLock();
    private static final ReentrantReadWriteLock.WriteLock WRITE_LOCK = LOCK.writeLock();

    private ImmutableSet<Player> players = ImmutableSet.of();

    @Override
    public void addPlayer(Player player) {
        WRITE_LOCK.lock();
        players = ImmutableSet.<Player>builder().addAll(players).add(player).build();
        WRITE_LOCK.unlock();
    }

    @Override
    public ImmutableSet<Player> getRegisteredPlayers() {
        READ_LOCK.lock();
        ImmutableSet<Player> currentPlayers = this.players;
        READ_LOCK.unlock();
        return currentPlayers;
    }
}
