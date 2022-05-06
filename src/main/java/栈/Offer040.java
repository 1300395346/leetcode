package 栈;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Offer040 {
    public static void main(String[] args) {
        Offer040 offer040 = new Offer040();
        int ans = offer040.maximalRectangle(new String[]{});
        System.out.println(ans);
    }

    public int maximalRectangle(String[] matrix) {
        int ans = 0;
        int row = matrix.length;
        if (row == 0) {
            return 0;
        }
        int cols = matrix[0].length();
        int[] heights = new int[cols];
        for (String s : matrix) {
            heights = getHeight(s, heights);
            ans = Math.max(ans, getAre(heights));
        }
        return ans;
    }

    public int getAre(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int len = height.length;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);
        for (int i = 0; i < len; ++i) {
            while (!stack.isEmpty() && height[stack.peek()] >= height[i]) {
                right[stack.peek()] = i;
                stack.pop();
            }
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        int ans = 0;
        for (int i = 0; i < len; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * height[i]);
        }

        return ans;
    }

    public int[] getHeight(String matrix, int[] heights) {
        int cols = matrix.length();
        int[] height = new int[cols];
        for (int i = 0; i < cols; ++i) {
            char temp = matrix.charAt(i);
            if (temp != '1') {
                height[i] = 0;
            } else {
                height[i] = heights[i] + 1;
            }
        }
        return height;
    }
}
