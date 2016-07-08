package decorators;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * A circle decorator that adds a filling to the circle.
 */
public class FillDrawingDecorator extends CircleDecorator {

    // the color of the filling
    private Color fillColor;

    /**
     * Constructor.
     *
     * @param fillColor the color of the fill
     * @param decorated the decorated circle
     */
    public FillDrawingDecorator(Color fillColor, Circle decorated) {
        super(decorated);

        this.fillColor = fillColor;
    }

    /**
     * Draws the filling and then draws the decorated circle.
     */
    @Override
    public void drawOn(DrawSurface ds) {

        ds.setColor(this.fillColor);
        ds.fillCircle(getX(), getY(), getRadius());

        super.drawOn(ds);
    }
}
