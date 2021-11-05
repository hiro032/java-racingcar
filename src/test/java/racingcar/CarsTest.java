package racingcar;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CarsTest {

    @ParameterizedTest
    @DisplayName("Cars 생성 테스트")
    @MethodSource
    void createCarsTest(int createCount, int expectedSize) {
        Cars cars = new Cars(createCount);

        assertThat(cars.size()).isEqualTo(expectedSize);
    }

    static Stream<Arguments> createCarsTest() {
        return Stream.of(
                Arguments.of(
                        1, 1
                ),
                Arguments.of(
                        2, 2
                )
        );
    }

    @ParameterizedTest
    @DisplayName("Cars 생성 예외 테스트")
    @ValueSource(ints = {-1, 0})
    void carsExceptionTest(int createCount) {
        assertThatThrownBy(() -> new Cars(createCount)).isInstanceOf(CreateCarCountException.class);
    }

}