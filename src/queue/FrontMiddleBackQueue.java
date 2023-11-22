package queue;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : mocun
 * @since : 2023/11/17
 */
public class FrontMiddleBackQueue {

    LinkedList<Integer> queue;

    int midIndex = 0;


    public FrontMiddleBackQueue() {
        queue = new LinkedList<>();
    }

    public void pushFront(int val) {
        queue.addFirst(val);
        calMiddleIndex();
    }

    public void pushMiddle(int val) {
        queue.add(midIndex, val);
        calMiddleIndex();
    }

    public void pushBack(int val) {
        queue.addLast(val);
        calMiddleIndex();
    }

    public int popFront() {
        if (queue.isEmpty()) {
            return -1;
        }
        int res = queue.removeFirst();
        calMiddleIndex();
        return res;
    }

    public int popMiddle() {
        if (queue.isEmpty()) {
            return -1;
        }
        int res;
        if (queue.size() % 2 == 0) {
            res = queue.remove(midIndex - 1);
        } else {
            res = queue.remove(midIndex);
        }
        calMiddleIndex();
        return res;
    }

    public int popBack() {
        if (queue.isEmpty()) {
            return -1;
        }
        int res = queue.removeLast();
        calMiddleIndex();
        return res;
    }

    private void calMiddleIndex() {
        int size = queue.size();
        midIndex = size % 2 == 0 ? (size / 2)  : size / 2;
    }

//    public static void main(String[] args) {
//        FrontMiddleBackQueue queue = new FrontMiddleBackQueue();
//        queue.pushFront(1);
//        queue.pushBack(2);
//        queue.pushMiddle(3);
//        queue.pushMiddle(4);
//
//        System.out.println(queue.popFront());
//        System.out.println(queue.popMiddle());
//        System.out.println(queue.popMiddle());
//        System.out.println(queue.popBack());
//        System.out.println(queue.popFront());
//    }

}
