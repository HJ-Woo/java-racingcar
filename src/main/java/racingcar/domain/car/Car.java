package racingcar.domain.car;

import racingcar.domain.strategy.MovingStrategy;

public class Car {

    private static final int DEFAULT_POSITION = 0;
    private static final int MOVING_DISTANCE = 1;
    private final Name name;
    private final Position position;

    public Car(String name) {
        this(new Name(name), new Position(DEFAULT_POSITION));
    }

    public Car(String name, int position) {
        this(new Name(name), new Position(position));
    }

    public Car(Name name, Position position) {
        this.name = name;
        this.position = position;
    }

    public Car move(MovingStrategy movableGenerator) {
        if (movableGenerator.isMovable()) {
            Name newName = new Name(name.getValue());
            Position newPosition = new Position(this.position.getValue() + MOVING_DISTANCE);
            return new Car(newName, newPosition);
        }

        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }

        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Car car = (Car) obj;
        return this.name.getValue().equals(car.getName().getValue()) &&
                this.position.getValue() == car.getPosition().getValue();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Name getName() {
        return name;
    }

    public Position getPosition() {
        return position;
    }
}