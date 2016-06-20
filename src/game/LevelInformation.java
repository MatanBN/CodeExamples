package game;

import sprites.Invader;

import java.util.List;

/**
 * The LevelInformation interface proviedes inforamtion on a level.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 22 May 2016
 */
public interface LevelInformation {

    /**
     * numberOfBalls method returns the number of balls of the level.
     *
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities method returns all of the balls velocities of this level.
     *
     * @return a list of ball velocities..
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed method returns the speed of the paddle in this level.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * paddleWidth method returns the width of the paddle in this level.
     *
     * @return the width of the paddle.
     */
    int paddleWidth();

    /**
     * levelName method returns the name of this level.
     *
     * @return the name this level.
     */
    String levelName();

    /**
     * blocks method returns a list of blocks of this level.
     *
     * @return a list of blocks of this level.
     */
    List<Invader> blocks();

    /**
     * numberOfBlocksToRemove method returns the number of blocks in this level.
     *
     * @return the number of blocks in this level.
     */
    int numberOfBlocksToRemove();
}
