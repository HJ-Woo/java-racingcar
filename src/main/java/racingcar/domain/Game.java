/*
 * 자동차 경주의 실행(입력)과 출력 및 종료를 담당하는 클래스
 *
 * @author hj-woo
 * @version 2.0
 * */
package racingcar.domain;

import racingcar.GameFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static racingcar.GameFactory.*;

public class Game {
    private Cars racingCars;
    private TryNo tryNo;

    /*
     * 게임 생성과 동시에 자동차 대수와 시도할 횟수를 확인하고,
     * GameFactory로부터 자동차 대수만큼의 자동차를 넘겨받는다.
     * */
    public Game(String[] strs, List<Object> inputArr) {
        for (int i = 0; i < strs.length; i++) {
            checkInputs(inputArr.get(i), strs[i]);
        }
    }

    public void checkInputs(Object input, String str) {
        if (str.equals(HOW_MANY_TRYS)) {
            tryNo = new TryNo((int) input);
        }
        if (str.equals(HOW_MANY_CARS)) {
            racingCars = GameFactory.cars((Integer) input);
        }
        if (str.equals(INPUT_CAR_NAMES)) {
            String[] names = String.valueOf(input).split(",");
            racingCars = GameFactory.namesCars(names);
        }
    }

    /*
     * 생성한 자동차들과 시도 횟수를 바탕으로 게임을 진행하고 결과를 출력한다.
     * 시도 횟수만큼 다 진행한 후에는 우승자를 출력한다.
     * */
    public ArrayList<Map<Name, Position>> play() {
        ArrayList<Map<Name, Position>> results = new ArrayList<>();
        if (tryNo.isZero()) {
            System.out.println("아무런 시도를 하지 않았습니다.");
        }
        while (tryNo.next()){
            racingCars.tryMove();
            results.add(racingCars.checkCarStatus());
        }
        return results;
    }
}
