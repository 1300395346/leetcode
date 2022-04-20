package 数组;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Offer007 {
    public static void main(String[] args) {
        Offer007 offer007 = new Offer007();
        int[] nums = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        List<List<Integer>> ans = offer007.threeSum(nums);
        System.out.println(ans);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);//java内置的数组排序函数
        for (int i = 0; i < len; ++i){//++i不需要一个临时变量存取，而i++需要，因此空间开销大，耗时长。
            if (nums[0] > 0) break;//排序过后若第一个数字仍旧大于零，则不可能存在三个数字之和为零的现象
            if (i > 0 && nums[i] == nums[i-1]){
                continue;
            }
            int start = i + 1;
            int end = len - 1;
            int target = -nums [i];
            while(start < end){
                if (nums[start] + nums[end] < target){
                    start++;
                } else if (nums[start] + nums[end] > target){
                    end--;
                } else {
                    result.add(Arrays.asList(nums[i],nums[start],nums[end]));
                    //和006不一样，满足条件的数字不唯一，同时若nums[start]与nums[start+1]相同，则可能会拿到相同的三元组，需要跳过
                    while (start < end && nums[start] == nums[start+1]) start++;
                    start++;
                }
            }
        }
        return result;
    }
}
