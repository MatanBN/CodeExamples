package patterns.iterator;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mcshlain on 7/4/15.
 */
public class Merger {

    public static List<Integer> merge(List<Integer> a, List<Integer> b) {
        List<Integer> result = new LinkedList<Integer>();
        CurrentIteratorAdapter<Integer> ai = new CurrentIteratorAdapter<Integer>(a.iterator());
        CurrentIteratorAdapter<Integer> bi = new CurrentIteratorAdapter<Integer>(b.iterator());
        while (ai.hasNext() && bi.hasNext()) {
            if (ai.value() < bi.value()) {
                result.add(ai.value());
                ai.next();
            } else {
                result.add(bi.value());
                bi.next();
            }
        }
        while (ai.hasNext()) {
            result.add(ai.value());
            ai.next();
        }
        while (bi.hasNext()) {
            result.add(bi.value());
            bi.next();
        }
        return result;
    }

}
