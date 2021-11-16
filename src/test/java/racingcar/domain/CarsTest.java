package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.MovingStrategy.RandomMovingStrategy;
import racingcar.exception.CreateCarCountException;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @ParameterizedTest
    @DisplayName("Cars 생성 테스트")
    @MethodSource
    void createCarsTest(String[] carNames, Cars expected) {
        Cars cars = Cars.from(carNames, RandomMovingStrategy.getInstance());

        assertThat(cars).isEqualTo(expected);
    }

    static Stream<Arguments> createCarsTest() {
        return Stream.of(
                Arguments.of(
                        new String[]{"aa", "bb", "cc"}, Cars.from(new String[]{"aa", "bb", "cc"}, RandomMovingStrategy.getInstance())
                )
        );
    }

    @Test
    @DisplayName("Cars 생성 예외 테스트")
    void carsExceptionTest() {
        assertThatThrownBy(() -> Cars.from(null, RandomMovingStrategy.getInstance())).isInstanceOf(CreateCarCountException.class);
    }

    @ParameterizedTest
    @DisplayName("이동 거리가 가장 긴 자동차가 우승한다")
    @MethodSource
    void findWinnerTest(Cars cars, Winners expectedWinners) {
        Winners findWinners = cars.findWinners();

        assertThat(findWinners).isEqualTo(expectedWinners);
    }

    static Stream<Arguments> findWinnerTest() {
        Car aa = Car.from(Name.from("AA"));
        aa.move(5);

        Car bb = Car.from(Name.from("BB"));
        bb.move(3);

        Car cc = Car.from(Name.from("CC"));
        cc.move(3);

        return Stream.of(
                Arguments.of(
                        new Cars(Arrays.asList(
                                aa, bb, cc
                        ), RandomMovingStrategy.getInstance())
                        , Winners.from(Collections.singletonList(aa))
                )
        );
    }

    @ParameterizedTest
    @DisplayName("우승자가 여러명 일 경우 테스트")
    @MethodSource
    void findWinnersTest(Cars cars, Winners expectedWinners) {
        Winners findWinners = cars.findWinners();

        assertThat(findWinners).isEqualTo(expectedWinners);
    }

    static Stream<Arguments> findWinnersTest() {
        Car aa = Car.from(Name.from("AA"));
        aa.move(5);

        Car bb = Car.from(Name.from("BB"));
        bb.move(5);

        Car cc = Car.from(Name.from("CC"));
        cc.move(3);

        return Stream.of(
                Arguments.of(
                        new Cars(Arrays.asList(
                                aa, bb, cc
                        ), RandomMovingStrategy.getInstance())
                        , Winners.from(Arrays.asList(aa, bb))
                )
        );
    }
}