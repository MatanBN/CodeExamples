package decorators;

/**
 * A decorator that adds the ability to move the circle in the 4 standard directions.
 */
public class MovingCircle extends CircleDecorator {

    // speed of movement
    private int speed;

    /**
     * Constructor.
     *
     * @param speed the speed at which to move the circle
     * @param decorated the circle to move
     */
    public MovingCircle(int speed, Circle decorated) {
        super(decorated);

        this.speed = speed;
    }

    /**
     * Move circle one step to the left
     */
    public void moveLeft() {
        setX(getX() - this.speed);
    }

    /**
     * Move circle one step to the right
     */
    public void moveRight() {
        setX(getX() + this.speed);
    }

    /**
     * Move circle one step up
     */
    public void moveUp() {
        setY(getY() - this.speed);
    }

    /**
     * Move circle one step down
     */
    public void moveDown() {
        setY(getY() + this.speed);
    }

}
