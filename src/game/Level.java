package gamelevels;

import game.LevelInformation;
import sprites.Block;
import sprites.Sprite;

import game.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The DirectHit class is a level information class that contains information on the first level DirectHit level.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public class Level implements LevelInformation {
    /**
     * numberOfBalls method returns the number of balls of the level.
     *
     * @return the number of balls.
     */
    public int numberOfBalls() {
        return 1;
    }

    /**
     * initialBallVelocities method returns all of the balls velocities of this level.
     *
     * @return a list of ball velocities..
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> myVels = new ArrayList<Velocity>();
        myVels.add(new Velocity(0, -2));
        return myVels;
    }

    /**
     * paddleSpeed method returns the speed of the paddle in this level.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return 5;
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
        return "Direct Hit";
    }

    /**
     * getBackground method returns a Sprite of the background of this level.
     *
     * @return a Sprite of this background's level.
     */
    @Override
    public Sprite getBackground() {

        return new Block(,Color.black);
    }

    /**
     * blocks method returns a list of blocks of this level.
     *
     * @return a list of blocks of this level.
     */
    @Override
    public List<Block> blocks() {
        Block block = new Block(375, 195, 10, 10, Color.red);
        List<Block> myBlocks = new ArrayList<Block>();
        myBlocks.add(block);
        return myBlocks;
    }

    /**
     * numberOfBlocksToRemove method returns the number of blocks in this level.
     *
     * @return the number of blocks in this level.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}