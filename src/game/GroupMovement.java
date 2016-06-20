package game;


import animations.GameLevel;
import biuoop.DrawSurface;
import environment.GameEnvironment;
import geometry.Point;
import listeners.HitListener;
import sprites.BaseBlock;
import sprites.Invader;
import sprites.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * Created by Matan on 6/15/2016.
 */
public class GroupMovement implements Sprite {
    private GameLevel level;
    private double intialSpeed;
    private double speed;
    private ArrayList<ArrayList<Invader>> invaders;
    private double mostLeftX;
    private double mostRightX;
    private double maxY;
    private HitListener hit;
    private long startTime;
    private GameEnvironment paddleEnv;

    public GroupMovement(GameLevel level, double speed, ArrayList<Invader> group, HitListener hit,
                         GameEnvironment paddleEnv) {
        this.level = level;
        this.intialSpeed = speed;
        this.speed = speed;
        invaders = new ArrayList<>();
        for (int k = 0; k < 10; ++k) {
            invaders.add(new ArrayList<Invader>());
        }
        for (int i=0; i<5;i++){
            for (int j=0; j<10; j++){
                 invaders.get(j).add(group.get(j+(i*10)));
            }
        }
        mostLeftX=invaders.get(0).get(0).getX();
        mostRightX = invaders.get(9).get(0).getRectangle().getMaxX();
        maxY = invaders.get(0).get(4).getY();
        this.hit = hit;
        this.paddleEnv = paddleEnv;
    }


    public void goDown() {
        maxY += 10;
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
        if (mostLeftX + speed * dt <= 20 || mostRightX + speed * dt >= 760) {
            goDown();
            speed *= -1.1;
        }
        if (maxY >= 500) {
            hit.hitEvent(new BaseBlock(0, 0, 0, 0, Color.black), this);
            return;
        }
        mostRightX += speed * dt;
        mostLeftX += speed * dt;
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                Point p = inv.getRectangle().getUpperLeft();
                p.setX(p.getX() + speed * dt);
            }
        }
        shoot();
    }

    public void remove (Invader inv){
        for (ArrayList<Invader> column : invaders){
            if (column.contains(inv)) {
                column.remove(inv);
                if (invaders.get(0).isEmpty()) {
                    int i = 1;
                    while (i < invaders.size() && invaders.get(i).isEmpty()) {
                        ++i;
                    }
                    if (i != invaders.size()) {
                        mostLeftX = invaders.get(i).get(0).getX();
                    }
                }
                if (invaders.get(invaders.size() - 1).isEmpty()) {
                    int i = invaders.size() - 2;
                    while (i >= 0 && invaders.get(i).isEmpty()) {
                        --i;
                    }

                    if (i >= 0) {
                        mostRightX = invaders.get(i).get(0).getRectangle().getMaxX();
                    }
                }
                break;
            }
        }
    }

    public void relocateInvaders() {
        int currentX = 40;
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                Point p = inv.getRectangle().getUpperLeft();
                p.setX(currentX);
                p.setY(inv.getFirstY());
            }
            currentX += 50;
        }
        mostLeftX = 40;
        mostRightX = 0;
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                if (inv.getX() > mostRightX) {
                    mostRightX = inv.getRectangle().getMaxX();
                }
            }
        }
        getMaxY();
        speed = intialSpeed;
        startTime = System.currentTimeMillis();
    }

    public void getMaxY() {
        double max = 0;
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                if (inv.getY() > max) {
                    max = inv.getY();
                }
            }
        }
        this.maxY = max;
    }

    public void shoot() {
        if (abs(System.currentTimeMillis() - startTime) > 500) {
            Random rand = new Random();
            int chosenColumn;
            do {
                chosenColumn = rand.nextInt(10);
            } while (invaders.get(chosenColumn).isEmpty());
            ArrayList<Invader> inv = invaders.get(chosenColumn);
            Invader shooter = inv.get(inv.size() - 1);
            double checkY = shooter.getY();
            double checkX = shooter.getX();

            level.createBall(new Point(shooter.getX() + 15, shooter.getY() + 40), 6, new Velocity(0, 500), Color.red,
                    paddleEnv);
            this.startTime = System.currentTimeMillis();


        }
    }
}
