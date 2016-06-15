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
 * The Block is a Collidable and a Sprite object that can block moving objects.
 * The class has methods to draw the block, and a method hit which will change the velocity of the object that hits
 * the block.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private geometry.Rectangle rectangle; // The rectangle shape of the block.
    private int hitsNumber; // The number of hits of the block.
    private int totalHits;
    private List<HitListener> hitListeners;
    private ArrayList<Sprite> fillers;

    /**
     * Block creates a new rectangle block by a given rectangle.
     *
     * @param r is a given rectangle.
     */
    public Block(Rectangle r) {
        this.rectangle = r;
        this.hitListeners = new ArrayList();
        fillers = new ArrayList<Sprite>();

    }

    /**
     * sprites.Block creates a new rectangle block by it's left corner coordinates,
     * width and height.
     *
     * @param x          is the x coordinate of left corner.
     * @param y          is the y coordinate of left corner.
     * @param width      is the rectangle's width.
     * @param height     is the rectangle's height.
     * @param frameColor the color of the block/rectangle frame.
     * @param fillers    the fillers of the block/rectangle.
     */
    public Block(int x, int y, int width, int height, Color frameColor, ArrayList<Sprite> fillers) {
        this.rectangle = new Rectangle(x, y, width, height, frameColor, fillers.get(0));
        this.hitListeners = new ArrayList();
        this.fillers = fillers;
    }

    /**
     * sprites.Block creates a new rectangle block by it's left corner coordinates,
     * width and height.
     *
     * @param x          is the x coordinate of left corner.
     * @param y          is the y coordinate of left corner.
     * @param width      is the rectangle's width.
     * @param height     is the rectangle's height.
     * @param frameColor the color of the block/rectangle frame.
     * @param filler     the filler of the block/rectangle.
     */
    public Block(int x, int y, int width, int height, Color frameColor, Sprite filler) {
        this.rectangle = new Rectangle(x, y, width, height, frameColor, filler);
        this.hitListeners = new ArrayList();
        this.fillers = new ArrayList<Sprite>();
        fillers.add(filler);
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
        if (this.hitsNumber > 0) {
            --this.hitsNumber;
        }
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

    /**
     * getHitPoints returns block's number of hits.
     *
     * @return the block's number of hits.
     */
    public int getHitPoints() {
        return hitsNumber;
    }

    /**
     * setHitsNumber sets the block's number of hits.
     *
     * @param hits the adjusted number of hits.
     */
    public void setHitsNumber(int hits) {
        totalHits = hits;
        hitsNumber = hits;
    }

    /**
     * setFiller method sets the filler according to the number of hit points left to the block.
     */
    public void setFiller() {
        if (fillers.size() > getHitPoints()) {
            rectangle.setFilling(fillers.get(totalHits - hitsNumber));
        }
    }

    /**
     * copy method copies a block and returns the copy.
     *
     * @return a copy of the block.
     */
    public Block copy() {
        Rectangle r = this.getRectangle();
        Block b = new Block(r.getX(), r.getY(), r.getWidth(), r.getHeight(), r.getColor(), fillers);
        b.setHitsNumber(totalHits);
        return b;
    }
}
