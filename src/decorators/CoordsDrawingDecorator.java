package decorators;

import biuoop.DrawSurface;

import java.awt.*;

/**
 * A circle decorator that adds the drawing of the coordinates.
 */
public class CoordsDrawingDecorator extends CircleDecorator {

    private static final int FONT_SIZE = 8;

    // color of the coordinates
    private Color textColor;

    /**
     * Constructor.
     *
     * @param textColor the color of the coordinates
     * @param decorated the decorated circle
     */
    public CoordsDrawingDecorator(Color textColor, Circle decorated) {
        super(decorated);

        this.textColor = textColor;
    }


    /**
     * Draw the decorated circle and then draw the coordinates.
     */
    @Override
    public void drawOn(DrawSurface ds) {
        super.drawOn(ds);

        ds.setColor(this.textColor);

        ds.drawText(getX(), getY(), "(" + getX()+ "," + getY() + ")", FONT_SIZE);

    }
}
