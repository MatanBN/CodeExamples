package environment;

import geometry.Point;

/**
 * The CollisionInfo contains a Collidable which is the collision itself and the
 * point of the collision.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class CollisionInfo {
    private Collidable myCollision;
    private Point collisionPoint;

    /**
     * Constructor to create the CollisionInfo.
     *
     * @param myCollision    which will be the collision itself and will be a Collidable object.
     * @param collisionPoint a Point which will be collision point.
     */
    public CollisionInfo(Collidable myCollision, Point collisionPoint) {
        this.myCollision = myCollision;
        this.collisionPoint = collisionPoint;
    }

    /**
     * collisionPoint returns the collision point.
     *
     * @return the collision point.
     */
    // the point at which the collision occurs.
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * collisionObject returns the Collidable collision object.
     *
     * @return the Collidable collision object.
     */
    public Collidable collisionObject() {
        return (Collidable) myCollision;
    }

}
