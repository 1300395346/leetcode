import java.util.Arrays;

public class Offer008 {
    public static void main(String[] args) {
        Offer008 offer008 = new Offer008();
        int[] nums = {2,1,2,4,3};
        int ans = offer008.minSubArrayLen(7,nums);
        System.out.println("结果为"+ans);
    }

    //前缀和+二分查找
    public int minSubArrayLen(int target, int[] nums){
        int len = nums.length;
        if (len == 0){
            return 0;
        }
        int[] sub = new int[len+1];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < len + 1 ; ++i){
            sub[i] = sub[i-1] + nums[i-1];//前缀和
        }
        for (int i = 0; i < len; ++i){
            int sum = target + sub[i];
            int bound = Arrays.binarySearch(sub,sum);
            if (bound < 0){
                bound = -bound - 1;
                //binarySearch搜索的值若存在于数组当中，则返回该值在数组中从1开始计数的下标值
                //若不存在数组当中，则返回大于该数字的第一个数的下标值（依旧是从1开始计数）并将其改为负数
            }
            if (bound <= len){
                ans = Math.min(ans,bound - i);
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }



    //滑动窗口
//    public int minSubArrayLen(int target, int[] nums){
//        int left = 0;
//        int right = 0;
//        int len = nums.length;
//        if (len == 0){
//            return 0;
//        }
//        int sum = 0;
//        int ans = Integer.MAX_VALUE;
//        while (right < len){
//            sum += nums[right];
//            while (sum >= target){
//                ans = Math.min(ans,right - left + 1);
//                sum -= nums[left];
//                left++;
//            }
//            right++;
//        }
//        return ans == Integer.MAX_VALUE ? 0 : ans;
//    }

    //超时
//    public int minSubArrayLen(int target, int[] nums){
//        int i = 0;
//        int len = nums.length;
//        int ans = len + 1;
//        while (i < len){
//            int j = i + 1;
//            int res = nums[i];
//            if (res >= target){
//                ans = 1;
//                break;
//            }
//            while (j < len){
//                res += nums[j];
//                int temp = j - i + 1;
//                if (res >= target && temp < ans){
//                    ans = temp;
//                    break;
//                }
//                ++j;
//            }
//            ++i;
//        }
//        if (ans > len){
//            ans = 0;
//        }
//        return ans;
//    }
}
