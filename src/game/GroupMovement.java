package game;


import biuoop.DrawSurface;
import environment.CollisionInfo;
import environment.GameEnvironment;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import sprites.Invader;
import sprites.Sprite;

import java.util.ArrayList;

/**
 * Created by Matan on 6/15/2016.
 */
public class GroupMovement implements Sprite {
    private double speed;
    private ArrayList<ArrayList<Invader>> invaders;
    private double mostLeftX;
    private double mostRightX;
    public GameEnvironment gameEnv;


    public GroupMovement(double speed, ArrayList<Invader> group, GameEnvironment gameEnv) {
        this.speed = speed;
        invaders = new ArrayList<>();
        for (int i=0; i<group.size();i++){
            for (int j=0; j<10; j++){
                 invaders.get(j).add(group.get(j+i*10));
            }
        }
        mostLeftX=invaders.get(0).get(0).getX();
        mostRightX=invaders.get(9).get(0).getX();
        this.gameEnv = gameEnv;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void goDown() {
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                inv.goDown();
            }
        }
    }

    @Override
    public void drawOn(DrawSurface d) {
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                inv.drawOn(d);
            }
        }
    }

    @Override
    public void timePassed(double dt) {
        // Get the trajectory.
        Line traj;
        if (speed > 0) {
            traj = new Line(new Point(mostRightX, 100),
                    new Point(mostRightX + speed * dt, 100));
        } else {
            traj = new Line(new Point(mostLeftX, 100),
                    new Point(mostLeftX + speed * dt, 100));
        }
        // Calculate the collision point if such exists.
        CollisionInfo myInfo = gameEnv.getClosestCollision(traj);
        if (myInfo.collisionPoint() != null) {
            myInfo.collisionObject().hit(this, myInfo.collisionPoint(), new Velocity(speed * dt, 0));
        }
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                Point p = inv.getRectangle().getUpperLeft();
                p.setX(p.getX() + speed * dt);
            }
        }
    }

    public void remove (Invader inv){
        for (ArrayList<Invader> column : invaders){
            if (column.contains(inv)) {
                column.remove(inv);
                break;
            }
        }
    }
}
