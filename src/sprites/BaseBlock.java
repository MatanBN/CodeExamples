package sprites;

import animations.GameLevel;
import biuoop.DrawSurface;
import environment.Collidable;
import game.Velocity;
import geometry.Rectangle;
import geometry.Point;
import geometry.Line;


import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The BaseBlock is a Collidable and a Sprite object that can block moving objects.
 * The class has methods to draw the block, and a method hit which will change the velocity of the object that hits
 * the block.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class BaseBlock implements Collidable, Sprite, HitNotifier {
    private geometry.Rectangle rectangle; // The rectangle shape of the block.
    private List<HitListener> hitListeners;

    /**
     * BaseBlock creates a new rectangle block by a given rectangle.
     *
     * @param r is a given rectangle.
     */
    public BaseBlock(Rectangle r) {
        this.rectangle = r;
        this.hitListeners = new ArrayList();
    }

    /**
     * BaseBlock creates a new rectangle block by a given rectangle.
     *
     * @param r    is a given rectangle.
     * @param fill the fill of the block.
     */
    public BaseBlock(Rectangle r, Sprite fill) {
        this.rectangle = r;
        this.rectangle.setFilling(fill);
        this.hitListeners = new ArrayList();
    }


    /**
     * BaseBlock creates a new rectangle block by it's left corner coordinates,
     * width and height.
     *
     * @param x          is the x coordinate of left corner.
     * @param y          is the y coordinate of left corner.
     * @param width      is the rectangle's width.
     * @param height     is the rectangle's height.
     * @param fill    the fillers of the block/rectangle.
     */
    public BaseBlock(int x, int y, int width, int height, Sprite fill) {
        this.rectangle = new Rectangle(x, y, width, height, fill);
        this.hitListeners = new ArrayList();
    }



    /**
     * drawOn method draws the block and the block's number of hits on a given
     * surface.
     *
     * @param d is the surface to draw the blocks on.
     */
    public void drawOn(DrawSurface d) {
        // Draw the rectangle shape of the block.
        rectangle.drawOn(d);
    }

    /**
     * getCollisionRectangle return the block's shape.
     *
     * @return the given rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * hit return the new velocity after the hit based on force the object
     * inflicted on us.
     *
     * @param hitter          is the specific ball that is about to hit the block
     * @param collisionPoint  is the collision point of an object with the block.
     * @param currentVelocity is the current velocity of the object that will collide with the block.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // Checks whether the collision point is on the right or left edges.
        if (checkCollisionSide(collisionPoint, rectangle.getLeftEdge())) {
            newVelocity.setDx(-currentVelocity.getDx());
        } else if (checkCollisionSide(collisionPoint, rectangle.getRightEdge())) {
            newVelocity.setDx(-currentVelocity.getDx());
        }
        // Checks whether the collision point is on the top or bottom edges.
        if (checkCollisionSide(collisionPoint, rectangle.getTopEdge())) {
            newVelocity.setDy(-currentVelocity.getDy());
        } else if (checkCollisionSide(collisionPoint, rectangle.getBottomEdge())) {
            newVelocity.setDy(-currentVelocity.getDy());
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * removeFromGame method removes the block from the gameLevel.
     *
     * @param gameLevel the GameLevel to remove the block from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * checkCollisionSide gets a collision point and an edge and checks. whether
     * the collision point is inside the given edge.
     *
     * @param collisionPoint is the collision point of the ball with the object.
     * @param edge           is a given edge of an object.
     * @return true if the collision point is inside the edge and false otherwise.
     */
    public boolean checkCollisionSide(Point collisionPoint, Line edge) {
        if (edge.inSegment(collisionPoint.getX(), collisionPoint.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Currently doesn't do anything.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void timePassed(double dt) {
    }

    /**
     * addToGame is in charge of adding the block as a sprites.Sprite and as a
     * environment.Collidable to the game's suitable lists.
     *
     * @param g is the game object we created.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * getRectangle gets the rectangle of the block.
     *
     * @return the rectangle that is the shape of the block.
     */
    public Rectangle getRectangle() {
        return rectangle;
    }

    /**
     * addHitListener method adds a hit listener to the block hit listeners collection.
     *
     * @param hl is the HitListener that is added.
     */
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * removeHitListener removes the hitListener from the block's hit listeners collection.
     *
     * @param hl is the HitListener that is removed.
     */
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * notifyHit method notifies all of the hit listeners that the block has been hit.
     *
     * @param hitter the ball the hitted the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }

    }

}
