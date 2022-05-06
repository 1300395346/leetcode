package 队列;

import java.util.ArrayDeque;
import java.util.Deque;

public class Offer041 {
    int size;
    int total;
    Deque<Integer> queue;

    public static void main(String[] args) {
        Offer041 offer041 = new Offer041(3);
        double a = offer041.next(1);
        double b = offer041.next(10);
        double c = offer041.next(3);
        double d = offer041.next(5);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    public Offer041(int size) {
        this.queue = new ArrayDeque<>();
        this.size = size;
        this.total = 0;
    }

    public double next(int val) {
        total += val;
        if (queue.size() == size) {
            total -= queue.getFirst();
            queue.removeFirst();
        }
        queue.addLast(val);
        return (double) total / queue.size();
    }
}