package racingcar.domain;

import racingcar.exception.RoundException;

import java.util.Objects;

public class Round {
    private static final int ADD_VALUE = 1;
    private static final int FIRST_ROUND = 0;

    private int round;

    private Round(int round) {
        validateRound(round);
        this.round = round;
    }

    public static Round from(int tryCount) {
        return new Round(tryCount);
    }

    public static Round from(Round round) {
        return new Round(round.round);
    }

    private void validateRound(int round) {
        if (round < FIRST_ROUND) {
            throw new RoundException();
        }
    }

    public boolean isOver() {
        return round == FIRST_ROUND;
    }

    public void counting() {
        this.round -= ADD_VALUE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round1 = (Round) o;
        return round == round1.round;
    }

    @Override
    public int hashCode() {
        return Objects.hash(round);
    }

}
