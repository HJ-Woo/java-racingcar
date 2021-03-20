/*
 * 자동차의 이름을 원시값으로 포장한 불변 객체
 *
 * @author hj-woo
 * @version 1.0
 * */

package racingcar.domain;

import racingcar.utils.ErrorMessage;
import racingcar.utils.StringUtils;

import java.util.Objects;

public class Name implements Comparable<Name>{

    private final String name;

    public Name(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException(ErrorMessage.NULL_NAME);
        }
        if (name.length() > 5) {
            throw new IllegalArgumentException(ErrorMessage.OVER_NAME);
        }
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Name o) {
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
