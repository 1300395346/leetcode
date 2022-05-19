package 队列.堆;

import java.util.PriorityQueue;

public class Offer059 {
    private PriorityQueue<Integer> heap;
    private int k;

    public Offer059(int k, int[] nums) {
        this.k = k;
        heap = new PriorityQueue<>(k);
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        heap.offer(val);
        if (heap.size() > k) {
            heap.poll();
        }
        return heap.peek();
    }

    public static void main(String[] args) {
        Offer059 k = new Offer059(3, new int[]{4, 5, 8, 2});
        System.out.println(k.add(3));
        System.out.println(k.add(5));
        System.out.println(k.add(10));
        System.out.println(k.add(9));
        System.out.println(k.add(4));
    }
}
