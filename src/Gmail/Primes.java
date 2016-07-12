import java.util.Iterator;

public class Primes implements Iterator<Integer> {
    private Iterator<Integer> nums;
    
    public Primes() {
        this.nums = new Count();
    }
    
    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        Integer item = this.nums.next();
        if (item > 1) {
            this.nums = new FilterMultiplies(this.nums, item);
        }
        return item;
    }

    @Override
    public void remove() {
    }

}
