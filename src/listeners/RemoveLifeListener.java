package listeners;

import animations.GameLevel;
import sprites.BaseBlock;
import sprites.LiveIndicator;
import sprites.Sprite;

/**
 * Created by Matan on 6/19/2016.
 */
public class RemoveLifeListener implements HitListener {
    private GameLevel level;
    private LiveIndicator liveIndicator;

    public RemoveLifeListener(GameLevel level, LiveIndicator liveIndicator) {
        this.level = level;
        this.liveIndicator = liveIndicator;
    }

    @Override
    public void hitEvent(BaseBlock beingHit, Sprite hitter) {
        liveIndicator.decrease();
        level.setRunning(false);

    }
}
