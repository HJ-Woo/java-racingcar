package step3_racingCar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class CarsTest {
    private Cars cars;
    private List<Car> carList;
    private static final int TRYNUM = 3;
    private static final int CARNUM = 4;

    @BeforeEach
    void setUp(){
        cars = new Cars(TRYNUM);

        carList = new LinkedList<Car>();
        Random random = new Random();
        for(int i=0; i<CARNUM; i++){
            Car car = new Car();
            car.setRandom(random);
            carList.add(car);
        }
    }

    @Test
    void createCars(){
        assertThat(cars.getTryNum()).isEqualTo(TRYNUM);
        assertThat(cars.getCars()).isInstanceOf(Collections.unmodifiableList(new LinkedList<Car>()).getClass());
        assertThat(cars.getCars().size()).isEqualTo(0);
    }

    @Test
    void setCarList(){
        cars.setCars(carList);
        assertThat(cars.getCars().size()).isEqualTo(CARNUM);
    }

    //TODO 테스트의 재설계 필요
    @Test
    @DisplayName("자동차 집합이 보유중인 자동차들의 전진 횟수를 테스트한다")
    void checkForwardTest(){
        assertThat(cars.checkForward().size()).isEqualTo(0);

        cars.setCars(carList);
        assertThat(cars.checkForward().size()).isEqualTo(CARNUM);
    }
}
