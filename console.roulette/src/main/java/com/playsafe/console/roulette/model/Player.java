package com.playsafe.console.roulette.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Getter
public class Player {
	private final String name;
	private final AtomicReference<BigDecimal> totalWin;
	private final AtomicReference<BigDecimal> totalBet;

	public Player(String name) {
		this(name, Optional.empty(), Optional.empty());
	}

	public Player(String name, Optional<BigDecimal> totalWin, Optional<BigDecimal> totalBet) {
		this.name = name;
		this.totalWin = new AtomicReference<>(totalWin.orElse(BigDecimal.ZERO));
		this.totalBet = new AtomicReference<>(totalBet.orElse(BigDecimal.ZERO));
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
}
