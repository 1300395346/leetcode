package 队列.堆;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Offer060 {
    public static void main(String[] args) {
        Offer060 offer060 = new Offer060();
        int[] ans = offer060.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
        System.out.println(Arrays.toString(ans));
    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] ans = new int[k];
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            if (heap.size() == k) {
                if (heap.peek()[1] < count) {
                    heap.poll();
                }
            }
            heap.offer(new int[]{num, count});
        }
        for (int i = 0; i < k; ++i) {
            ans[i] = heap.poll()[0];
        }
        return ans;
    }

    //list.sort中b-a是倒序，a-b是顺序
//    public int[] topKFrequent(int[] nums, int k) {
//        int[] ans = new int[k];
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int num : nums) {
//            map.put(num, map.getOrDefault(num, 0) + 1);
//        }
//        List<Integer> list = new ArrayList<>(map.keySet());
//        list.sort((a, b) -> map.get(b) - map.get(a));
//        for (int i = 0; i < k; ++i) {
//            ans[i] = list.get(i);
//        }
//
//        return ans;
//    }
}
