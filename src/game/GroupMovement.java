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


    public GroupMovement(double speed, ArrayList<Invader> group) {
        this.speed = speed;
        invaders = new ArrayList<>();
        for (int i=0; i<group.size();i++){
            for (int j=0; j<10; j++){
                 invaders.get(j).add(group.get(j+i*10));
            }
        }
        mostLeftX=invaders.get(0).get(0).getX();
        mostRightX=invaders.get(9).get(0).getX();
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
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                inv.timePassed(dt);
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
