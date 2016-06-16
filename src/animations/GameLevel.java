package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import environment.Collidable;
import environment.GameEnvironment;
import game.Counter;
import game.GroupMovement;
import game.Velocity;
import game.LevelInformation;
import geometry.Rectangle;
import geometry.Point;
import listeners.BallRemover;

import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprites.*;

import java.awt.Color;
import java.util.List;

import static java.lang.Math.abs;

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
    private AnimationRunner runner; // The animation runner of the game/
    private boolean running; // A boolean variable if the game runs or not.
    private KeyboardSensor keyboard; // The keyboard sensor of the game.
    private LevelInformation myLevel; // The level information of the game.
    private Paddle paddle; // The paddle of the game.
    private LiveIndicator liveIndicator; // The live indicator of the game.
    private long startTime;
    private GroupMovement gm;

    /**
     * Constructor to create the GameLevel.
     *
     * @param level  the LevelInformation.
     * @param key    the keyboard sensor of the game.
     * @param runner the animation runner of the game.
     */
    public GameLevel(LevelInformation level, KeyboardSensor key, AnimationRunner runner, GroupMovement gm) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blockCounter = new Counter();
        myLevel = level;
        this.keyboard = key;
        this.runner = runner;
        this.gm = gm;
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
        geometry.Rectangle borders = new Rectangle(800, 600, Color.black);
        liveIndicator = lives;
        addSprite(new BaseBlock(borders));

        // Create the paddle.
        Rectangle paddleRec = new Rectangle(360 - myLevel.paddleWidth() / 2, borders.getHeight() - 51,
                myLevel.paddleWidth(), 10, Color.green);
        paddle = new Paddle(keyboard, paddleRec, borders, myLevel.paddleSpeed());
        paddle.addToGame(this);
        addDeathBorder(borders.getMaxY(), borders.getMaxX(), 20);
        addDeathBorder(0, borders.getMaxX(), 20);

        // Create the score indicator
        Rectangle infoFrame = new Rectangle(0, 0, borders.getMaxX(), 20);
        Rectangle infoFrameFilled = new Rectangle(infoFrame.getUpperLeft(), borders.getMaxX(), 20, Color.white);
        BaseBlock playInfo = new BaseBlock(infoFrameFilled);
        playInfo.addToGame(this);


        List<Invader> invaders = myLevel.blocks();
        for (Invader invader : invaders) {
            invader.addHitListener(new BlockRemover(this, blockCounter));
            invader.addHitListener(new ScoreTrackingListener(myScore.getScore()));
            invader.addHitListener(new BallRemover(this));
            invader.setGm(gm);
            invader.addToGame(this);
        }
        blockCounter.increase(myLevel.numberOfBlocksToRemove());
        int shieldPixelWidth = 2;
        int shieldPixelHeight = 4;

        //Create shields
        for (int i = 0; i < 3; i++) {
            int shieldStartX = i * 200;

            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 75; k++) {
                    Rectangle r = new Rectangle(100 + k * shieldPixelWidth + shieldStartX, 500 + j * shieldPixelHeight,
                            shieldPixelWidth, shieldPixelHeight, Color.cyan);
                    BaseBlock shield = new BaseBlock(r);
                    shield.addHitListener(new BlockRemover(this, new Counter()));
                    shield.addHitListener(new BallRemover(this));
                    shield.addToGame(this);
                }
            }
        }

        addSprite(lives);
        addSprite(myScore);
        addSprite(new LevelIndicator(myLevel.levelName()));
        this.startTime = System.currentTimeMillis();
    }

    private void addDeathBorder(int y, int width, int height) {
        // Create the death border.
        Rectangle r = new Rectangle(0, y, width, height);
        BaseBlock deathBorder = new BaseBlock(new Rectangle(0, y, width, height));
        deathBorder.addHitListener(new BallRemover(this));
        addCollidable(deathBorder);
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
    }

    /**
     * playOneTurn method resets the game to the start position.
     */
    public void playOneTurn() {
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
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (abs(System.currentTimeMillis() - startTime) > 350) {
                Rectangle paddleRec = paddle.getCollisionRectangle();
                createBall(new Point(paddleRec.getX() + paddleRec.getWidth() / 2, paddleRec.getY() - 10),
                        3, new Velocity(0, -500));
                this.startTime= System.currentTimeMillis();
            }
        }

        if (blockCounter.getValue() == 0) {
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