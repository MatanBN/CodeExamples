import java.util.Iterator;

public class FilterMultiplies implements Iterator<Integer> {
    private Iterator<Integer> seq;
    private int n;
    
    public FilterMultiplies(Iterator<Integer> seq, int n){
        this.seq = seq;
        this.n = n;
    }
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        int item = this.seq.next();
        while (item % this.n == 0) {
            item = this.seq.next();
        }
        return item;
    }

    @Override
    public void remove() {
        // TODO Auto-generated method stub
        
    }
}
