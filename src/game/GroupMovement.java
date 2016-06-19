package game;


import biuoop.DrawSurface;
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


    public GroupMovement(double speed, ArrayList<ArrayList<Invader>>) {
        this.speed = speed;
        invaders = invaders;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void goDown() {
        for (ArrayList<Invader> columns : invaders) {
            for (Invader)
                inv.goDown();
        }
    }

    @Override
    public void drawOn(DrawSurface d) {

    }

    @Override
    public void timePassed(double dt) {

    }
}
