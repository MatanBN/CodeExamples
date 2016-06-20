package game;

import sprites.*;

import java.awt.Color;

import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The DirectHit class is a level information class that contains information on the first level DirectHit level.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class Level implements LevelInformation {
    private Rectangle r;
    private int levelNum;

    /**
     * Level constructor constructs a new level with r as the game frame.
     * @param r the game frame.
     */
    public Level(Rectangle r, int levelNum) {
        this.r = r;
        this.levelNum = levelNum;
    }

    /**
     * numberOfBalls method returns the number of balls of the level.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 0;
    }

    /**
     * initialBallVelocities method returns all of the balls velocities of this level.
     *
     * @return a list of ball velocities..
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> myVels = new ArrayList<Velocity>();
        return myVels;
    }

    /**
     * paddleSpeed method returns the speed of the paddle in this level.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return 550;
    }

    /**
     * paddleWidth method returns the width of the paddle in this level.
     *
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return 80;
    }

    /**
     * levelName method returns the name of this level.
     *
     * @return the name this level.
     */
    @Override
    public String levelName() {
        return "Battle No." + levelNum;
    }

    /**
     * blocks method returns a list of blocks of this level.
     *
     * @return a list of blocks of this level.
     */
    @Override
    public List<Invader> blocks() {
        List<Invader> levelInvaders = new ArrayList<Invader>();
        int currentX = 40;
        int currentY = 50;
        ImageParser ip = new ImageParser();
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 10; ++j) {
                Rectangle r = new Rectangle(currentX, currentY, 40, 30);
                Invader invader = new Invader(r, ip.imageFromString("enemy.png"), currentY);
                levelInvaders.add(invader);
                currentX += 50;
            }
            currentY += 40;
            currentX = 40;
        }
        return levelInvaders;
    }

    /**
     * numberOfBlocksToRemove method returns the number of blocks in this level.
     *
     * @return the number of blocks in this level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 50;
    }
}