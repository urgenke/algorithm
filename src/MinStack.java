import java.util.HashMap;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @author : mocun
 * @since : 2023/10/24
 */
public class MinStack {

    Stack<Integer> stack = new Stack<>();
//    int min = Integer.MAX_VALUE;

    TreeSet<Integer> minTree = new TreeSet<>();
    HashMap<Integer, Integer> map = new HashMap<>();

    public MinStack() {

    }

    public void push(int val) {
        stack.push(val);
        minTree.add(val);
        if (map.containsKey(val)) {
            Integer integer = map.get(val);
            map.put(val, integer + 1);
        } else {
            map.put(val, 1);
        }
    }

    public void pop() {
        int pop = stack.pop();
        Integer integer = map.get(pop);
        if (integer == 1) {
            map.remove(pop);
            minTree.remove(pop);
        } else {
            map.put(pop, integer - 1);
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minTree.first();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(0);
        minStack.push(1);
        minStack.push(0);
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }
}
