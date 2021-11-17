package racingcar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.MovingStrategy.RandomMovingStrategy;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RacingGameTest {

    private RacingGame racingGame;

    @BeforeEach
    void setUp() {
        racingGame = RacingGame.of(Cars.from(new String[] {"a", "b", "c"}, RandomMovingStrategy.getInstance()));
    }

    @ParameterizedTest
    @DisplayName("게임이 n번 진행될 동안 자동차는 전진, 정지 할 수 있다.")
    @MethodSource
    void racingGameProgressTest(int finalRound, int expected) {
        GameLog gameLog = racingGame.play(Round.from(finalRound));

        assertThat(gameLog.getRoundLogs().size()).isEqualTo(expected);
    }

    static Stream<Arguments> racingGameProgressTest() {
        return Stream.of(
                Arguments.of(
                        0, 0
                ),
                Arguments.of(
                        1, 1
                ),
                Arguments.of(
                        2, 2
                ),
                Arguments.of(
                        3, 3
                )
        );
    }
}