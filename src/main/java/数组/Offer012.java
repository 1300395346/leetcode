package 数组;

import java.util.Arrays;

public class Offer012 {
    public static void main(String[] args) {
        Offer012 offer012 = new Offer012();
        int[] nums = {1,7,3,6,5,6};
        int ans = offer012.pivotIndex(nums);
        System.out.println(ans);
    }

    public int pivotIndex(int[] nums){
        int total = Arrays.stream(nums).sum();
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; ++i){
            if (sum * 2 + nums[i] == total){
                return i;
            }
            sum += nums[i];
        }
        System.out.println(sum);
        return -1;
    }
}
