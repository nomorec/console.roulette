package com.playsafe.console.roulette.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Getter
@Setter
public class Bet {

    @Value("${thirty.six.factor}")
    private double thirtySixFactor;
    @Value("${two.factor}")
    private double twoFactor;

    private final Optional<Integer> betNumber;
    private final BigDecimal betAmount;
    private final Type type;
    private final AtomicReference<Optional<BigDecimal>> winnings = new AtomicReference<>(Optional.empty());
    private final AtomicReference<Optional<Outcome>> outcome = new AtomicReference<>(Optional.empty());

    public Bet(Optional<Integer> betNumber, BigDecimal betAmount, Type type) {
        this.betNumber = betNumber;
        this.betAmount = betAmount;
        this.type = type;
    }

    public Optional<BigDecimal> getWinnings(int number) {
        switch (type) {
            case NUMBER:
                return multiplyWinnings(betNumber.filter(n -> n == number).map(n -> betAmount), BigDecimal.valueOf(thirtySixFactor));
            case ODD:
                return multiplyWinnings(Optional.of(betAmount).filter(amount -> number % 2 != 0), BigDecimal.valueOf(twoFactor));
            case EVEN:
                return multiplyWinnings(Optional.of(betAmount).filter(amount -> number % 2 == 0), BigDecimal.valueOf(twoFactor));
            default:
                return Optional.empty();
        }
    }

    private Optional<BigDecimal> multiplyWinnings(Optional<BigDecimal> winnings, BigDecimal multiplier) {
        return winnings.map(amount -> amount.multiply(multiplier));
    }
}