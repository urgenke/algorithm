import java.util.PriorityQueue;

/**
 * @author : mocun
 * @since : 2023/10/26
 */
public class MedianFinder {

    private int count;

    private PriorityQueue<Integer> queue;

    public MedianFinder() {
        count = 0;
        queue = new PriorityQueue<>();

    }

    public void addNum(int num) {
        queue.add(num);
        count++;
    }

    public double findMedian() {
        Object[] array = queue.toArray();
        if (count % 2 == 0) {
            int index = count / 2;
            Object o1 = array[index - 1];
            Object o2 = array[index];
            double d1 = Double.valueOf((Integer) o1);
            double d2 = Double.valueOf((Integer) o2);
            return (d1 + d2) / 2;
        } else {
            Object o = array[count / 2];
            return Double.valueOf((Integer) o);
        }
    }

    public static void main(String[] args) {
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(3);
        medianFinder.addNum(2);
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());
    }


}
