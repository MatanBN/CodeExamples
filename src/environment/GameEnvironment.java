package environment;

import biuoop.DrawSurface;
import geometry.Rectangle;
import geometry.Line;
import sprites.BaseBlock;

import java.util.ArrayList;

/**
 * The GameEnvironment class contains an ArrayList of Collidables, and has
 * methods to add a Collidable object, get the closest collision from the list
 * of Collidables, and draw all of the Collidables.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class GameEnvironment {
    private ArrayList collidables; // The ArrayList of Collidables.

    /**
     * The constructor of environment.GameEnvironment.
     */
    public GameEnvironment() {
        collidables = new ArrayList();
    }

    /**
     * addCollidable method adds the given Collidable to the environment.
     *
     * @param c the Collidable object to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }


    /**
     * removeCollidable method removes the given Collidable from the environment.
     *
     * @param c the Collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

    /**
     * getClosestCollision method returns the CollisionInfo of the closest
     * collision from all of the Collidables.
     *
     * @param trajectory of the object that will hit one of the Collidables.
     * @return the CollisionInfo of the collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle r = ((Collidable) collidables.get(0)).getCollisionRectangle();
        CollisionInfo min = new CollisionInfo((Collidable) collidables.get(0),
                trajectory.closestIntersectionToStartOfLine(r));
        // Find the closest collision from the given trajectory.
        ArrayList coll = new ArrayList(collidables);
        for (Object o : coll) {
            r = ((Collidable) o).getCollisionRectangle();
            // Check if the current Collidable wasn't been hit from the
            // trajectory.
            if (min.collisionPoint() == null) {
                min = new CollisionInfo((Collidable) o, trajectory.closestIntersectionToStartOfLine(r));
                continue;
            }
            CollisionInfo collision = new CollisionInfo((Collidable) o, trajectory.closestIntersectionToStartOfLine(r));
            if (collision.collisionPoint() == null) {
                continue;
            }
            // Check if the current Collidable the currently closest to the
            // trajectory start point.
            if (collision.collisionPoint().distance(trajectory.start()) < min.collisionPoint()
                    .distance(trajectory.start())) {
                min = collision;
            }
        }
        return min;
    }

    /**
     * drawOn method draws all of the Collidables.
     *
     * @param d the drawSurface to draw the Collidables.
     */
    public void drawOn(DrawSurface d) {
        for (Object o : collidables) {
            ((BaseBlock) o).drawOn(d);
        }
    }

}