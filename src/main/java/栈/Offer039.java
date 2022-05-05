package 栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Offer039 {
    public static void main(String[] args) {
        Offer039 offer039 = new Offer039();
        int ans = offer039.largestRectangleArea(new int[]{2, 4});
        System.out.println(ans);
    }

    //单调栈+常数优化
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < len; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }

        return ans;
    }

    //利用单调栈寻找左右两侧的柱子，然后计算面积
//    public int largestRectangleArea(int[] heights) {
//        int len = heights.length;
//        int[] left = new int[len];
//        int[] right = new int[len];
//
//        Deque<Integer> stack = new ArrayDeque<>();
//        for (int i = 0; i < len; ++i) {
//            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
//                stack.pop();
//            }
//            left[i] = stack.isEmpty() ? -1 : stack.peek();
//            stack.push(i);
//        }
//
//        stack.clear();
//
//        for (int i = len - 1; i >= 0; --i) {
//            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
//                stack.pop();
//            }
//            right[i] = stack.isEmpty() ? len : stack.peek();
//            stack.push(i);
//        }
//
//        int ans = 0;
//        for (int i = 0; i < len; ++i) {
//            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
//        }
//        return ans;
//    }

    //时间复杂度O(n^2)，超时
//    public int largestRectangleArea(int[] heights) {
//        int max = heights[0];
//        int len = heights.length;
//        for (int i = 0; i < len; ++i) {
//            int h = heights[i];
//            for (int j = i; j < len; ++j) {
//                int wide = j - i + 1;
//                h = Math.min(h, heights[j]);
//                max = Math.max(max, wide * h);
//            }
//        }
//        return max;
//    }
}
