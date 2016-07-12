import java.util.Iterator;

public class Count implements Iterator<Integer> {
    private int n;
    
    public Count() {
        this.n = 2;
    }
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        return n++;
    }

    @Override
    public void remove() {
    }

}
