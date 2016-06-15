package sprites;

import biuoop.DrawSurface;
import geometry.Rectangle;

import java.awt.Color;


/**
 * ColorSprite class is a background for block/level with color.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 11 June 2016
 */
public class ColorSprite implements Sprite {
    private Rectangle frame;
    private Color c; // The color of the background.

    /**
     * ColorSprite constructor.
     *
     * @param frame the frame to be draw on.
     * @param c     the color of the ColorSprite.
     */
    public ColorSprite(Rectangle frame, Color c) {
        this.frame = frame;
        this.c = c;
    }

    /**
     * drawOn method draws the ColorSprite according to the frame and color.
     *
     * @param d the DrawSurface to draw the sprite on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(c);
        d.fillRectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
    }

    /**
     * Currently doesn't do anything.
     *
     * @param dt the time interval.
     */
    @Override
    public void timePassed(double dt) {

    }

}