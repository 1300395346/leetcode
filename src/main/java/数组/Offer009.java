package 数组;

public class Offer009 {
    public static void main(String[] args) {
        Offer009 offer009 = new Offer009();
        int[] nums = {10,5,2,6};
        int ans = offer009.numSubarrayProductLessThanK(nums,100);
        System.out.println(ans);
    }

    public int numSubarrayProductLessThanK(int[] nums, int k){
        int result = 0;
        int left = 0;
        int right = 0;
        int temp = 1;
        int len = nums.length;
        while(right < len){
            temp *= nums[right];
            while (left <= right && temp >= k){
                temp /= nums[left++];
            }
            if (left <= right){
                result += right - left + 1;
            }
            right++;
        }
        return result;
    }
}
