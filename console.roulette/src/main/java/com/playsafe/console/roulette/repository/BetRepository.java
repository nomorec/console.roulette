package com.playsafe.console.roulette.repository;

import java.util.Set;

import com.playsafe.console.roulette.model.PlayerBet;

public interface BetRepository {
	void addBet(PlayerBet playerBet);

	Set<PlayerBet> getCurrentGameBets();
}
