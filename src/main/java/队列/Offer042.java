package 队列;

import java.util.ArrayDeque;
import java.util.Queue;

public class Offer042 {
    Queue<Integer> queue;

    public static void main(String[] args) {
        Offer042 offer042 = new Offer042();
        System.out.println(offer042.ping(1));
        System.out.println(offer042.ping(100));
        System.out.println(offer042.ping(3001));
        System.out.println(offer042.ping(3002));
    }


    public Offer042() {
        this.queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - 3000) {
            queue.poll();
        }
        return queue.size();
    }
}
