package racingcar.domain;

import racingcar.exception.CreateCarCountException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Cars {
    private static final int MIN_CREATE_COUNT = 1;

    private final MovingStrategy movingStrategy;
    private final List<Car> cars;

    private Cars(String[] carNames, MovingStrategy movingStrategy) {
        validateMovingStrategy(movingStrategy);
        validateCreateCount(carNames);

        this.movingStrategy = movingStrategy;
        this.cars = Arrays.stream(carNames)
                .map(Name::from)
                .map(Car::from)
                .collect(Collectors.toList());
    }

    protected Cars (List<Car> cars, MovingStrategy movingStrategy) {
        this.cars = cars;
        this.movingStrategy = movingStrategy;
    }

    public static Cars from(String[] carNames, MovingStrategy movingStrategy) {
        return new Cars(carNames, movingStrategy);
    }

    private void validateMovingStrategy(MovingStrategy movingStrategy) {
        if (Objects.isNull(movingStrategy)) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCreateCount(String[] carsName) {
        if (carsName == null) {
            throw new CreateCarCountException();
        }
        if (carsName.length < MIN_CREATE_COUNT) {
            throw new CreateCarCountException();
        }
    }

    public void move() {
        cars.forEach(car -> car.move(movingStrategy.generateNumber()));
    }

    public Winners findWinners() {
        Position winnerPosition = findWinnerPosition();

        List<Car> winners = cars.stream().filter(car -> car.isSamePosition(winnerPosition))
                .collect(Collectors.toList());

        return Winners.from(winners);
    }

    private Position findWinnerPosition() {
        Position winnerPosition = Position.init();

        for (Car car : cars) {
            Position position = car.currentPosition();
            winnerPosition = winnerPosition.biggerPosition(position);
        }

        return winnerPosition;
    }

    public List<Car> getHistory() {
        return Collections.unmodifiableList(cars);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cars other = (Cars) o;
        if (cars.size() != other.cars.size()) return false;
        for (int i = 0; i < cars.size(); i++) {
            if (!cars.get(i).equals(other.cars.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }

}
