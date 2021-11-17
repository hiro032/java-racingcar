package racingcar.domain;

import racingcar.exception.PositionException;

import java.util.Objects;

public class Position {
    private static final int INIT_POSITION = 0;

    private int position;

    private Position(int position) {
        validatePositionValue(position);
        this.position = position;
    }

    private void validatePositionValue(int position) {
        if (position < INIT_POSITION) {
            throw new PositionException(position);
        }
    }

    public static Position init() {
        return new Position(INIT_POSITION);
    }

    public static Position from(int position) {
        return new Position(position);
    }

    public static Position from(Position position) {
        return new Position(position.position);
    }

    public Position of() {
        return new Position(this.position);
    }

    public void move(int moveValue) {
        this.position += moveValue;
    }

    public Position biggerPosition(Position other) {
        if (this.position > other.position) {
            return new Position(this.position);
        }
        return other;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return position == position1.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

}
