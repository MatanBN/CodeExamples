package decorators;

import biuoop.DrawSurface;

/**
 * Abstract Circle Decorator.
 */
public abstract class CircleDecorator implements Circle {

    // The object that is being decorated
    private Circle decorated;

    /**
     * Constructor that wraps around any object of type circle.
     *
     * @param decorated the object to decorate
     */
    protected CircleDecorator(Circle decorated) {
        this.decorated = decorated;
    }

    /**
     * Forward the call to the decorated object
     */
    @Override
    public int getX() {
        return this.decorated.getX();
    }

    /**
     * Forward the call to the decorated object
     */
    @Override
    public int getY() {
        return this.decorated.getY();
    }

    /**
     * Forward the call to the decorated object
     */
    @Override
    public void setX(int value) {
        this.decorated.setX(value);
    }

    /**
     * Forward the call to the decorated object
     */
    @Override
    public void setY(int value) {
        this.decorated.setY(value);
    }

    /**
     * Forward the call to the decorated object
     */
    @Override
    public int getRadius() {
        return this.decorated.getRadius();
    }

    /**
     * Forward the call to the decorated object
     */
    @Override
    public void setRadius(int value) {
        this.decorated.setRadius(value);
    }

    /**
     * Forward the call to the decorated object
     */
    @Override
    public void drawOn(DrawSurface ds) {
        this.decorated.drawOn(ds);
    }

}
