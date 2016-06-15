package listeners;

/**
 * HitNotifier indicates that objects that implement it send notifications when they are being hit.
 *
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public interface HitNotifier {

    /**
     * addHitListener adds hl as a listener to hit events.
     *
     * @param hl is the HitListener that is added.
     */
    void addHitListener(HitListener hl);

    /**
     * removeHitListener removes hl from the list of listeners to hit events.
     *
     * @param hl is the HitListener that is removed.
     */
    void removeHitListener(HitListener hl);
}