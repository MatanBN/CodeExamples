// space
public class Stack<E> implements ImmutableStack<E> {
    private final ImmutableStack<E> prev;
    private final E data;
    public Stack(ImmutableStack<E> prev, E data) {
        this.prev = prev;
        this.data = data;
    }
    @Override
    public ImmutableStack<E> push(E data) {
        return new Stack<E>(this, data);
    }
    @Override
    public ImmutableStack<E> pop() {
        return this.prev;
    }

    @Override
    public E top() {
        return this.data;
    }
    @Override
    public int size() {
        return 1 + this.prev.size();
    }
    @Override
    public boolean isEmpty() {
        return false;
    }
    @Override
    public Iterator<E> iterator() {
        return new StackIterator<E>(this);
    }
    private static class StackIterator<E> implements Iterator<E> {
        ImmutableStack<E> stack;
        public StackIterator(ImmutableStack<E> stack) {
            this.stack = stack;
        }
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        @Override
        public E next() {
            E current = this.stack.top();
            this.stack = this.stack.pop();
            return current;
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
