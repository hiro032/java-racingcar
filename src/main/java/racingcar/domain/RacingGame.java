package racingcar.domain;

public class RacingGame {
    private final Cars cars;
    private final GameLog gameLog;

    private RacingGame(Cars cars) {
        this.cars = cars;
        this.gameLog = GameLog.init();
    }

    public static RacingGame of(Cars cars) {
        return new RacingGame(cars);
    }

    public GameLog play(Round finalRound) {
        Round current = Round.init();

        while (!current.equals(finalRound)) {
            cars.move();
            cars.recode(current.nextRound(), gameLog);
            current = current.nextRound();
        }
        return gameLog;
    }

}
