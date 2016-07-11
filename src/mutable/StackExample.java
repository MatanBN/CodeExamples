package stack.mutable;

import java.util.Iterator;

/**
 * Created by mcshlain on 7/4/15.
 */
public class StackExample {

    public static void main(String[] args) {

        Stack<Integer> stack = new LinkedListStack<Integer>();

        System.out.println("Stack size: " + stack.size());
        System.out.println("Stack isEmpty: " + stack.isEmpty());

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        System.out.println("Stack top: " + stack.top());
        System.out.println("Stack size: " + stack.size());
        System.out.println("Stack isEmpty: " + stack.isEmpty());

        System.out.println("Direct iteration (through iterator)");
        Iterator<Integer> itor = stack.iterator();
        while (itor.hasNext()) {
            System.out.println(itor.next());
        }

        System.out.println("Indirect iteration (through for each)");
        for (Integer i : stack) {
            System.out.println(i);
        }

    }

}
