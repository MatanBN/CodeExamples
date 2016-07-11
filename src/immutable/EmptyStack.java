package stack.immutable;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by mcshlain on 7/4/15.
 */
public class EmptyStack<E> implements ImmutableStack<E> {

    public EmptyStack() {
    }

    @Override
    public ImmutableStack<E> push(E data) {
        return new Stack<E>(this, data);
    }

    @Override
    public ImmutableStack<E> pop() {
        throw new RuntimeException("pop empty!");
    }

    @Override
    public E top() {
        throw new RuntimeException("top empty!");
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public E next() {
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
