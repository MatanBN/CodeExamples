package game;


import sprites.Invader;

import java.util.ArrayList;

/**
 * Created by Matan on 6/15/2016.
 */
public class GroupMovement {
    private double speed;
    private ArrayList<Invader> invaders;

    public GroupMovement(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setInvaders(ArrayList<Invader> invaders) {
        this.invaders = invaders;
    }

    public void goDown() {
        for (Invader inv : invaders) {
            inv.goDown();
        }
    }
}
