package 数组;

import java.util.HashMap;

public class Offer011 {
    public static void main(String[] args) {
        Offer011 offer011 = new Offer011();
        int[] nums = {1,1,1,0,0,1,1,1,0};
        int ans = offer011.findMaxLength(nums);
        System.out.println(ans);
    }

    public int findMaxLength(int[] nums){
        int len = nums.length;
        int count = 0;
        int result = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);
        for (int i = 0; i < len; ++i){
            if (nums[i] == 1){
                count++;
            } else {
                count--;
            }
            if (map.containsKey(count)){
                result = Math.max(result,i-map.get(count));
            } else {
                map.put(count,i);
            }
        }
        return result;
    }
}
