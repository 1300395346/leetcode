package 数组;

import java.util.HashMap;
import java.util.Map;

public class Offer057 {
    public static void main(String[] args) {
        Offer057 offer057 = new Offer057();
        boolean ans = offer057.containsNearbyAlmostDuplicate(new int[]{7, 1, 3}, 2, 3);
        System.out.println(ans);
    }

    //桶排序思想
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        Map<Long, Long> map = new HashMap<>();
        long w = (long) t + 1;
        for (int i = 0; i < len; ++i) {
            long id = getID(nums[i], w);
            if (map.containsKey(id)) {
                return true;
            }
            if (map.containsKey(id - 1) && Math.abs(nums[i] - map.get(id - 1)) < w) {
                return true;
            }
            if (map.containsKey(id + 1) && Math.abs(nums[i] - map.get(id + 1)) < w) {
                return true;
            }
            map.put(id, (long) nums[i]);
            if (i >= k) {
                map.remove(getID(nums[i - k], w));
            }
        }
        return false;
    }

    public Long getID(long x, long w) {
        if (x >= 0) {
            return x / w;
        }
        return (x + 1) / w - 1;
    }

    //滑动窗口+有序集合
//    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//        int len = nums.length;
//        TreeSet<Long> set = new TreeSet<>();
//        for (int i = 0; i < len; ++i) {
//            Long temp = set.ceiling((long) nums[i] - (long) t);
//            if (temp != null && temp <= (long) nums[i] + (long) t) {
//                return true;
//            }
//            set.add((long) nums[i]);
//            if (i >= k) {
//                set.remove((long) nums[i - k]);
//            }
//        }
//        return false;
//    }

    //枚举
//    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
//        int len = nums.length;
//        for (int i = 0; i < len; ++i) {
//            for (int j = i + 1; j - i <= k && j < len; ++j) {
//                if (Math.abs((long) nums[i] - (long) nums[j]) <= t) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

}
