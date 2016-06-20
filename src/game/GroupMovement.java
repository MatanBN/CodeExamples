package game;


import animations.GameLevel;
import biuoop.DrawSurface;
import environment.GameEnvironment;
import geometry.Point;
import listeners.HitListener;
import sprites.Ball;
import sprites.BaseBlock;
import sprites.Invader;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * GroupMovement holds the formation of the invaders and in charge of their movement and shots.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
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

    /**
     * Constructor creates a list of columns of the invaders and initializes the parameters.
     *
     * @param level     the game level of the game.
     * @param speed     the speed of the formation.
     * @param group     list of invaders.
     * @param hit       hit listener.
     * @param paddleEnv the paddle of the game.
     */
    public GroupMovement(GameLevel level, double speed, ArrayList<Invader> group, HitListener hit,
                         GameEnvironment paddleEnv) {
        this.level = level;
        this.intialSpeed = speed;
        this.speed = speed;
        invaders = new ArrayList<>();
        for (int k = 0; k < 10; ++k) {
            invaders.add(new ArrayList<Invader>());
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                invaders.get(j).add(group.get(j + (i * 10)));
            }
        }
        mostLeftX = invaders.get(0).get(0).getX();
        mostRightX = invaders.get(9).get(0).getRectangle().getMaxX();
        maxY = invaders.get(0).get(4).getY();
        this.hit = hit;
        this.paddleEnv = paddleEnv;
    }

    /**
     * goDown changes the y coordinate of the formation.
     */
    public void goDown() {
        maxY += 10;
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                inv.goDown();
            }
        }
    }

    /**
     * drawOn method draws the aliens on a given surface.
     *
     * @param d the surface to draw the aliens on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        for (ArrayList<Invader> column : invaders) {
            for (Invader inv : column) {
                inv.drawOn(d);
            }
        }
    }

    /**
     * timePassed is in charge of moving the aliens, checking whether they hit the borders every call.
     *
     * @param dt specifies the amount of seconds passed since the last call.
     */
    @Override
    public void timePassed(double dt) {
        if (mostLeftX + speed * dt <= 20 || mostRightX + speed * dt >= 760) {
            goDown();
            speed *= -1.1;
        }
        if (maxY >= 480) {
            hit.hitEvent(new BaseBlock(0, 0, 0, 0, Color.black), new Ball(0, 0, 0, Color.red, new GameEnvironment()));
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

    /**
     * remove is in charge of removing an alien from the game.
     *
     * @param inv the invader to remove.
     */
    public void remove(Invader inv) {
        for (ArrayList<Invader> column : invaders) {
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

    /**
     * relocateInvaders changes the formation's location on the screen.
     */
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

    /**
     * getMaxY checks which line is the lowest in the formation by the lowest alien that exist.
     */
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

    /**
     * shoot is in charge of making the lowest aliens in every column to shot randomly.
     */
    public void shoot() {
        if (abs(System.currentTimeMillis() - startTime) > 500) {
            Random rand = new Random();
            int chosenColumn;
            do {
                chosenColumn = rand.nextInt(10);
            } while (invaders.get(chosenColumn).isEmpty());
            ArrayList<Invader> inv = invaders.get(chosenColumn);
            Invader shooter = inv.get(inv.size() - 1);

            level.createBall(new Point(shooter.getX() + 15, shooter.getY() + 40), 6, new Velocity(0, 500), Color.red,
                    true);
            this.startTime = System.currentTimeMillis();


        }
    }
}
