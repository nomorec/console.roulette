package com.playsafe.console.roulette.model;

import lombok.Getter;

@Getter
public class PlayerBet {
	private final Player player;
	private final Bet bet;

	public PlayerBet(Player player, Bet bet) {
		this.player = player;
		this.bet = bet;
	}
}