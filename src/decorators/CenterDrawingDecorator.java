package decorators;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * A circle decorator that adds the drawing of the center point.
 */
public class CenterDrawingDecorator extends CircleDecorator {

    private static final int CENTER_SIZE = 6;

    // center point color
    private Color centerColor;

    /**
     * Constructor.
     *
     * @param centerColor the color of center point
     * @param decorated the decorated circle
     */
    public CenterDrawingDecorator(Color centerColor, Circle decorated) {
        super(decorated);

        this.centerColor = centerColor;
    }

    /**
     * Draw the decorated circle and then draw the center point.
     */
    @Override
    public void drawOn(DrawSurface ds) {
        super.drawOn(ds);

        ds.setColor(this.centerColor);

        ds.drawLine(
                getX() - CENTER_SIZE / 2,
                getY(),
                getX() + CENTER_SIZE / 2,
                getY());

        ds.drawLine(
                getX(),
                getY() - CENTER_SIZE / 2,
                getX(),
                getY() + CENTER_SIZE / 2);

    }
}
