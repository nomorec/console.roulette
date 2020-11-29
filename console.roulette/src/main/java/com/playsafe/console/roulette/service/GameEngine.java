package com.playsafe.console.roulette.service;

import com.playsafe.console.roulette.model.Player;
import com.playsafe.console.roulette.repository.PlayerRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.Set;

@Component
class GameEngine {
	private static final PrimitiveIterator.OfInt WINNING_NUMBERS = new Random().ints(0, 36).iterator();

	private final PlayerRepository playerRepository;

	public GameEngine(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Scheduled(fixedRate = 30000, initialDelay = 30000)
	void landBall() {
		int winningNumber = nextWinningNumber();
		Set<Player> playerBets = playerRepository.getRegisteredPlayers();
	}

	private int nextWinningNumber() {
		return WINNING_NUMBERS.nextInt();
	}

}
