import java.util.Iterator;
import java.util.List;

/**
 * Created by Matan on 7/10/2016.
 */
public class PairsIterator<E> implements Iterator<Pair<E>> {
    private Iterator<E> it1;
    private Iterator<E> it2;

    public PairsIterator(List<E> lst) {
        it1 = lst.iterator();
        it2 = lst.iterator();
        it2.next();
    }

    @Override
    public boolean hasNext() {
        return it2.hasNext();
    }

    @Override
    public Pair<E> next() {
        if (!this.hasNext()) {
            return null;
        }
        return new Pair<E>(it1.next(), it2.next());

    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
