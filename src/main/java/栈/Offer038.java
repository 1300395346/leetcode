package 栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Offer038 {
    public static void main(String[] args) {
        Offer038 offer038 = new Offer038();
        int[] ans = offer038.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(ans));
    }

    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int len = temperatures.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; ++i) {
            int temp = temperatures[i];
            while (!stack.isEmpty() && temp > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                ans[prevIndex] = i - prevIndex;
            }
            stack.push(i);
        }
        return ans;
    }

    //时间复杂度O(nm)
//    public int[] dailyTemperatures(int[] temperatures) {
//        int len = temperatures.length;
//        int[] next = new int[101];
//        int[] ans = new int[len];
//        Arrays.fill(next, Integer.MAX_VALUE);
//        for (int i = len - 1; i >= 0; --i) {
//            int temp = Integer.MAX_VALUE;
//            for (int j = temperatures[i] + 1; j < 101; ++j) {
//                if (next[j] < temp) {
//                    temp = next[j];
//                }
//            }
//            if (temp < Integer.MAX_VALUE) {
//                ans[i] = temp - i;
//            }
//            next[temperatures[i]] = i;
//        }
//        return ans;
//    }

    //时间复杂度O(n^2)
//    public int[] dailyTemperatures(int[] temperatures) {
//        int len = temperatures.length;
//        int[] ans = new int[len];
//        for (int i = 0; i < len - 1; ++i) {
//            for (int j = i + 1; j < len; ++j) {
//                if (temperatures[i] < temperatures[j]) {
//                    ans[i] = j - i;
//                    break;
//                }
//            }
//        }
//        ans[len - 1] = 0;
//        return ans;
//    }
}
