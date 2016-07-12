
import java.util.Iterator;
import java.util.List;

/**
 * Created by Matan on 7/10/2016.
 */
public class PairsFrom<E> implements Iterable<Pair<E>> {
    private List<E> myList;
    public PairsFrom(List<E> lst) {
        myList = lst;
    }
    @Override
    public Iterator<Pair<E>> iterator() {
        return new PairsIterator(myList);
    }
}
