package patterns.factory.good;

import patterns.factory.bad.MovingSprite;

/**
 * Created by mcshlain on 6/19/16.
 */
public interface BulletFactory {

    MovingSprite create(double x, double y);

}
