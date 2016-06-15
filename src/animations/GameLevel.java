package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import environment.Collidable;
import environment.GameEnvironment;
import game.Counter;
import game.Velocity;
import game.LevelInformation;
import geometry.Rectangle;
import geometry.Point;
import listeners.BallRemover;

import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.LiveIndicator;
import sprites.Paddle;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.ScoreIndicator;
import sprites.Block;

import sprites.LevelIndicator;
import sprites.Ball;

import java.awt.Color;
import java.util.List;

/**
 * The GameLevel class contains a a SpriteCollection which will be all the sprites in
 * the game, a GameEnvironment which will include all of the Collidable objects,
 * and a GUI which will be our game windows.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */

public class GameLevel implements Animation {
    private SpriteCollection sprites; // All of the sprites in the game.
    private GameEnvironment environment; // The game environment.
    private Counter blockCounter; // The block counter of the game.
    private Counter ballCounter; // The ball counter of the game.
    private AnimationRunner runner; // The animation runner of the game/
    private boolean running; // A boolean variable if the game runs or not.
    private KeyboardSensor keyboard; // The keyboard sensor of the game.
    private LevelInformation myLevel; // The level information of the game.
    private Paddle paddle; // The paddle of the game.
    private LiveIndicator liveIndicator; // The live indicator of the game.

    /**
     * Constructor to create the GameLevel.
     *
     * @param level  the LevelInformation.
     * @param key    the keyboard sensor of the game.
     * @param runner the animation runner of the game.
     */
    public GameLevel(LevelInformation level, KeyboardSensor key, AnimationRunner runner) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter();
        ballCounter = new Counter();
        myLevel = level;
        this.keyboard = key;
        this.runner = runner;
    }

    /**
     * addCollidable method adds a Collidable object to the game.
     *
     * @param c the Collidable object to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * addSprite method adds a Sprite object to the game.
     *
     * @param s the Sprite object to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * remove Collidable method removes a Collidable object from the game.
     *
     * @param c the Collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.removeCollidable(c);
    }

    /**
     * removeSprite method removes a Sprite object from the game.
     *
     * @param s the Sprite object to add.
     */
    public void removeSprite(Sprite s) {
        sprites.removeSprite(s);
    }

    /**
     * initialize method draws the borders, ball, paddle and blocks on a new
     * gui.
     *
     * @param lives   is the number of live
     * @param myScore is number of the score.
     */
    public void initialize(LiveIndicator lives, ScoreIndicator myScore) {
        geometry.Rectangle borders = new Rectangle(800, 600);
        liveIndicator = lives;
        addSprite(myLevel.getBackground());

        // Create the paddle.
        Rectangle paddleRec = new Rectangle(360 - myLevel.paddleWidth() / 2, borders.getHeight() - 51,
                myLevel.paddleWidth(), 10);
        paddle = new Paddle(keyboard, paddleRec, borders, myLevel.paddleSpeed(), Color.black,
                new ColorSprite(paddleRec, Color.GREEN));
        paddle.addToGame(this);

        // Create the death border.
        Block deathBorder = new Block(0, borders.getMaxY(), borders.getMaxX(), 20, Color.white,
                new ColorSprite(borders, Color.white));
        deathBorder.addHitListener(new BallRemover(this, ballCounter));
        addCollidable(deathBorder);

        // Create the score indicator
        Rectangle infoFrame = new Rectangle(0, 0, borders.getMaxX(), 20);
        Rectangle infoFrameFilled = new Rectangle(infoFrame.getUpperLeft(), borders.getMaxX(), 20, Color.white,
                new ColorSprite(infoFrame, Color.white));
        Block playInfo = new Block(infoFrameFilled);
        playInfo.addToGame(this);

        // Create the top border.
        createBorder(0, playInfo.getRectangle().getMaxY(), borders.getMaxX(), 20, Color.gray);

        // Create the left border.
        createBorder(0, playInfo.getRectangle().getMaxY() + 20, 20, borders.getMaxY(), Color.gray);

        // Create the right border.
        createBorder(borders.getMaxX() - 20, playInfo.getRectangle().getMaxY() + 20, 20, borders.getMaxY(), Color.gray);

        List<Block> myBlocks = myLevel.blocks();
        for (Block block : myBlocks) {
            Block b = block.copy();
            b.addHitListener(new BlockRemover(this, blockCounter));
            b.addHitListener(new ScoreTrackingListener(myScore.getScore()));
            b.addHitListener(new BlockChanger());
            b.addToGame(this);
        }
        blockCounter.increase(myLevel.numberOfBlocksToRemove());

        addSprite(lives);
        addSprite(myScore);
        addSprite(new LevelIndicator(myLevel.levelName()));
    }

    /**
     * createBorder method creates a border for the game.
     *
     * @param x      the start x coordinate.
     * @param y      the start y coordinate.
     * @param width  the width of the border.
     * @param height the height of the border.
     * @param c      the color of the border.
     */
    private void createBorder(int x, int y, int width, int height, Color c) {
        Block block = new Block(x, y, width, height, Color.black,
                new ColorSprite(new Rectangle(x, y, width, height), c));
        block.setHitsNumber(0);
        block.addToGame(this);
    }

    /**
     * createBall method creates a new ball to the game.
     *
     * @param p      the center point of the ball.
     * @param radius the radius of the ball.
     * @param v      the velocity of the ball.
     */
    public void createBall(Point p, int radius, Velocity v) {
        Ball ball = new Ball(p, radius, Color.WHITE, v, environment);
        ball.addToGame(this);
        ballCounter.increase(1);
    }

    /**
     * createBalls method creates balls according to the level's number of balls.
     */
    private void createBalls() {
        // Create the balls.
        List<Velocity> myVelocities = myLevel.initialBallVelocities();
        int howMany = myLevel.numberOfBalls();
        int xDistance = 10;
        int yDistance = 10;
        for (int i = 0; i < howMany; ++i) {
            if (i == 0) {
                createBall(new Point(375, 400), 3, myVelocities.get(i));
            } else if (i % 2 == 0) {
                createBall(new Point(375 + xDistance, 400 + yDistance), 3, myVelocities.get(i));
                yDistance += 20;
                xDistance += xDistance;
            } else {
                createBall(new Point(375 - xDistance, 400 + yDistance), 3, myVelocities.get(i));
            }
        }
    }

    /**
     * playOneTurn method resets the game to the start position.
     */
    public void playOneTurn() {
        this.createBalls(); // create the balls
        paddle.relocatePaddle(360 - myLevel.paddleWidth() / 2);
        this.runner.run(new CountdownAnimation(2, 3, sprites)); // countdown before turn starts.

        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * shouldStop method returns the not value of running.
     *
     * @return the not value of the running variable.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * doOneFrame method draws the game on the screen.
     *
     * @param d  the drawSurface to draw on.
     * @param dt specifies the amount of seconds passed since the last call.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        // the logic from the previous playOneTurn method goes here.
        // the `return` or `break` statements should be replaced with
        // this.running = false;
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new StopScreenDecorator(keyboard, "j", new PauseScreen(keyboard)));
        }
        if (blockCounter.getValue() == 0) {
            this.running = false;
        } else if (ballCounter.getValue() == 0) {
            liveIndicator.decrease();
            this.running = false;
        }
    }


    /**
     * getBlockCounter returns the current number of blocks.
     *
     * @return the current number of blocks.
     */
    public Counter getBlockCounter() {
        return blockCounter;
    }


}