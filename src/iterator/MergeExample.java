package patterns.iterator;

import java.util.*;

/**
 * Created by mcshlain on 7/4/15.
 */
public class MergeExample {

    public static void main(String[] args) {

        List<Integer> listA = new ArrayList<Integer>();
        List<Integer> listB = new ArrayList<Integer>();

        Random rand = new Random();

        for (int i = 0; i < 5 ; i++) {
            listA.add(rand.nextInt(100));
            listB.add(rand.nextInt(100));
        }

        // Print unsorted
        System.out.println("Unsorted list A");
        printIterator(listA.iterator());

        System.out.println("Unsorted list B");
        printIterator(listB.iterator());

        // sort
        Collections.sort(listA);
        Collections.sort(listB);

        // Print sorted
        System.out.println("Sorted list A");
        printIterator(listA.iterator());

        System.out.println("Sorted list B");
        printIterator(listB.iterator());

        // Print merged
        List<Integer> merged = Merger.merge(listA, listB);
        System.out.println("Sorted And Merged");
        printIterator(merged.iterator());

    }

    private static void printIterator(Iterator<Integer> itor){
        while(itor.hasNext()){
            System.out.println(itor.next());
        }
    }

}
