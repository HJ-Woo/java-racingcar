package step3.racingCar.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CarsTest {

    @InjectMocks
    private Cars cars;
    @Spy
    private List<Car> carList;
    @Mock
    private Car car;

    private static final int CAR_NUM = 4;
    private static final int INIT_NUM = 0;

    @BeforeEach
    void setUp() {
        carList = new ArrayList<>();
        MockitoAnnotations.initMocks(this);
        for (int i = 0; i < CAR_NUM; i++) {
            carList.add(car);
        }
        cars = new Cars(carList);
    }

    @Test
    void createAndSetCars() {

        /*
         * Cars 내부의 List<Car>을 확인하는 로직
         * extracting은 List에 담아서 반환하기 때문에 2차원 리스트의 크기로 확인해야한다.
         * */

        assertThat(cars).extracting("cars")
                .hasOnlyElementsOfType((new ArrayList<Car>()).getClass())
                .hasSameSizeAs(new List[]{carList});

    }


    @ParameterizedTest
    @CsvSource("0, 1, 2")
    @DisplayName("자동차 집합이 보유중인 자동차들의 전진 횟수 리스트를 반환하는지 테스트한다.")
    void checkForwardTest(int input) {
        when(car.getForwardNum()).thenReturn(input);
        List<Integer> forwardNums = cars.checkForward();

        verify(car, times(CAR_NUM)).getForwardNum();
        assertThat(forwardNums.size()).isEqualTo(CAR_NUM);
        assertThat(forwardNums).containsOnly(input);
    }

    @Test
    @DisplayName("자동차 집합이 전진할지 여부를 확인했는지 리스트들의 메소드 호출 횟수를 확인한다.")
    void tryForwardTest() {
        cars.tryForward();
        verify(car, times(CAR_NUM)).goForward();
    }

    @Test
    @DisplayName("(Mock X) 자동차 집합이 보유중인 자동차들의 이름과 전진횟수를 확인한다.")
    void checkCarStatus() {
        List<Car> testCarList = new ArrayList<>();
        String name = "name";
        for (int i = 0; i < CAR_NUM; i++) {
            testCarList.add(new Car(name + i));
        }
        Cars testCars = new Cars(testCarList);
        Map<String, Integer> carStatus = testCars.checkCarStatus();

        assertThat(carStatus.size()).isEqualTo(CAR_NUM);
        for (int i = 0; i < CAR_NUM; i++) {
            assertThat(carStatus.get(name + i)).isEqualTo(INIT_NUM);
        }
    }
}
