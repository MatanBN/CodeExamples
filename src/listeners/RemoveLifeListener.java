package listeners;

import animations.GameLevel;
import sprites.Ball;
import sprites.BaseBlock;
import sprites.LiveIndicator;

/**
 * removeLifeListener is in charge of removing lives from the gameLevel, and tells the game to start the level again.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class RemoveLifeListener implements HitListener {
    private GameLevel level;
    private LiveIndicator liveIndicator;

    /**
     * Constructor gets the gamelevel and live indicator of the game.
     *
     * @param level         the level of the game.
     * @param liveIndicator the counter of lives.
     */
    public RemoveLifeListener(GameLevel level, LiveIndicator liveIndicator) {
        this.level = level;
        this.liveIndicator = liveIndicator;
    }

    /**
     * hitEvent notifies that there is a hit and decreases the number of lives left
     * and set the running level to false.
     *
     * @param beingHit is the block tat is being hit.
     * @param hitter   is the Ball that's doing the hitting.
     */
    @Override
    public void hitEvent(BaseBlock beingHit, Ball hitter) {
        liveIndicator.decrease();
        level.setRunning(false);

    }
}
