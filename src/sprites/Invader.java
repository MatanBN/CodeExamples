package sprites;

import biuoop.DrawSurface;
import environment.CollisionInfo;
import environment.GameEnvironment;
import game.GroupMovement;
import game.Velocity;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
import java.awt.Image;

/**
 * Created by Matan on 6/15/2016.
 */
public class Invader extends BaseBlock {
    private GroupMovement gm;
    private Image invaderImage;
    private GameEnvironment gameEnv; // The game environment of the ball.

    public Invader(Rectangle r, Image img) {
        super(r);
        this.invaderImage = img;
    }

    public Invader(int x, int y, int width, int height, Image img) {
        super(new Rectangle(x, y, width, height));
        this.invaderImage = img;
    }

    @Override
    public void timePassed(double dt) {
        Rectangle invaderRec = super.getRectangle();
        Point p = invaderRec.getUpperLeft();
        // Get the trajectory.
        Line traj = new Line(new Point(invaderRec.getMaxX(), invaderRec.getY()),
                new Point(invaderRec.getMaxX() + gm.getSpeed() * dt, invaderRec.getY()));
        // Calculate the collision point if such exists.
        CollisionInfo myInfo = gameEnv.getClosestCollision(traj);
        if (myInfo.collisionPoint() != null) {
            myInfo.collisionObject().hit(this, myInfo.collisionPoint(), new Velocity(gm.getSpeed() * dt, 0));
        }
        p.setX(p.getX() + gm.getSpeed() * dt);
    }

    public void goDown() {
        Rectangle r = super.getRectangle();
        Point p = r.getUpperLeft();
        p.setY(p.getY() + 10);
    }

    public void drawOn(DrawSurface d) {
        d.drawImage(this.getRectangle().getX(), this.getRectangle().getY(), invaderImage);
    }

    public void setGm(GroupMovement gm) {
        this.gm = gm;
    }

    public void setGameEnv(GameEnvironment gameEnv) {
        this.gameEnv = gameEnv;
    }
}
