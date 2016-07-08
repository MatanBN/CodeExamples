package decorators;

/**
 * A circle decorator that keeps the circle always inside a boundary by performing a mod operation.
 */
public class SpaceBendingDecorator extends CircleDecorator {

    // the coordinates space to keep the circle in
    private int spaceWidth;
    private int spaceHeight;

    /**
     * Constructor.
     *
     * @param spaceWidth the width of the space to wrap the coordinates around
     * @param spaceHeight the height of the space to wrap the coordinates around
     * @param decorated the decorated circle
     */
    public SpaceBendingDecorator(int spaceWidth, int spaceHeight, Circle decorated) {
        super(decorated);

        this.spaceWidth = spaceWidth;
        this.spaceHeight = spaceHeight;
    }

    /**
     * Change x coordinate (but keep it inside the boundary)
     */
    @Override
    public void setX(int value) {
        super.setX((value + this.spaceWidth) % this.spaceWidth);
    }

    /**
     * Change y coordinate (but keep it inside the boundary)
     */
    @Override
    public void setY(int value) {
        super.setY((value + this.spaceHeight) % this.spaceHeight);
    }
}
