# 剑指Offer 007.数组中和为0的三个数

给定一个包含 `n` 个整数的数组 `nums`，判断 `nums` 中是否存在三个元素 `a` ，`b` ，`c` ，使得 `a + b + c = 0` ？请找出所有和为 0 且 **不重复** 的三元组。

2022/4/14 第一次刷

初见思路：暴力三重for循环。显而易见是可行的，但是是否会超出时间限制值得商榷，因此考虑其他思路。若将数组排序过后，将三个数中的其中一个固定，就是剑指Offer 006的问题了。即寻找`nums`中任意两个元素`a`和`b`之和为`-c`，同时为了保证所得元祖不重复，应该保证`c`的值不重复。

代码如下：

```java
class Solution{
        public List<List<Integer>> threeSum(int[] nums) {
        int len = nums.length;
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);//java内置的数组排序函数
        for (int i = 0; i < len; i++){
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
```

结果：通过！时间复杂度O(n^2)，空间复杂度O(n)

tips:在书写for循环时for(int i = 0; i < len; i++)和for(int i = 0; i < len; ++i)在实际运行的时候循环的次数是一样的，但是在**java中，i++语句是需要一个临时变量取存储返回自增前的值，而++i不需要**，简而言之，在for循环当中，使用++i所需的时间会比i++少。
