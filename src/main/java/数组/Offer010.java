package 数组;

import java.util.HashMap;

public class Offer010 {
    public static void main(String[] args) {
        Offer010 offer010 = new Offer010();
        int[] nums = {1,2,3};
        int ans = offer010.subarraySum(nums, 3);
        System.out.println(ans);
    }

    //前缀和+哈希表查找
    public int subarraySum(int[] nums, int k){
        HashMap<Integer,Integer> map = new HashMap<>();
        int pre = 0;
        int ans = 0;
        map.put(0,1);
        for (int num : nums) {
            pre += num;
            int value = map.getOrDefault(pre, 0) + 1;
            if (map.containsKey(pre - k)){
                ans += map.get(pre - k);
            }
            map.put(pre, value);
        }
        return ans;
    }

    //枚举所有连续子数组
//    public int subarraySum(int[] nums, int k){
//        int len = nums.length;
//        int res = 0;
//        int ans = 0;
//        for (int i = 0; i < len; ++i){
//            for (int j = i; j < len; ++j){
//                res += nums[j];
//                System.out.println("res:" + res);
//                if (res == k){
//                    ans++;
//                }
//                if (j == len-1){
//                    res = 0;
//                    break;
//                }
//            }
//        }
//        return ans;
//    }
}
