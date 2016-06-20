package sprites;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * SpriteCollection is a list of the sprites in the game.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    /**
     * The constructor creates a new list for the sprites.
     */
    public SpriteCollection() {
        sprites = new ArrayList<Sprite>();
    }

    /**
     * addSprite adds a given sprite to the list.
     *
     * @param s is a new sprite
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * removeSprite removes a given sprite from the list.
     *
     * @param s is the sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /**
     * notifyAllTimePassed calls the timePassed method on all sprites.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void notifyAllTimePassed(double dt) {
        ArrayList<Sprite> spritesCopy = new ArrayList<>(sprites);
        for (Sprite s : spritesCopy) {
            s.timePassed(dt);
        }
    }

    /**
     * drawAllOn calls drawOn on all sprites.
     *
     * @param d is the surface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> spritesCopy = new ArrayList<>(sprites);

        for (Sprite s : spritesCopy) {
            s.drawOn(d);
        }
    }

    /**
     * getSprite returns a specific sprite from the list.
     *
     * @param index is the index of a given sprite.
     * @return the required sprite from the list.
     */
    public Sprite getSprite(int index) {
        return sprites.get(index);
    }

    /**
     * getSprites returns a list of the sprites of the game.
     *
     * @return a list of the sprites of the game.
     */
    public ArrayList<Sprite> getSprites() {
        return sprites;
    }
}
