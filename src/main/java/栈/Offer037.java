package 栈;

import java.util.Arrays;
import java.util.Stack;

public class Offer037 {
    public static void main(String[] args) {
        Offer037 offer037 = new Offer037();
        int[] ans = offer037.asteroidCollision(new int[]{-2, -1, 1, 2});
        System.out.println(Arrays.toString(ans));
    }

    //优化后的代码
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        int len = asteroids.length;
        int index = 0;
        while (index < len) {
            if (stack.isEmpty() || stack.peek() < 0 || asteroids[index] > 0) {
                stack.push(asteroids[index]);
            } else if (stack.peek() <= -asteroids[index]) {
                if (stack.pop() < -asteroids[index]) {//此时已经将栈顶元素弹出了，若栈顶元素等于当前元素，则进入下一个元素的判断
                    continue;
                }
            }
            ++index;
        }
        int size = stack.size();
        int[] ans = new int[size];
        for (int i = size - 1; i >= 0; --i) {
            ans[i] = stack.pop();
        }
        return ans;
    }

//    public int[] asteroidCollision(int[] asteroids) {
//        Stack<Integer> stack = new Stack<>();
//        for (int asteroid : asteroids) {
//            judge(asteroid, stack);
//        }
//        int len = stack.size();
//        int[] ans = new int[len];
//        for (int i = len - 1; i >= 0; --i) {
//            ans[i] = stack.pop();
//        }
//        return ans;
//    }
//
//    public void judge(int cur, Stack<Integer> stack) {
//        if (stack.isEmpty()) {
//            stack.push(cur);
//        } else {
//            int temp = stack.pop();
//            if (temp * cur > 0) {
//                stack.push(temp);
//                stack.push(cur);
//            } else {
//                if (temp > cur) {
//                    if (temp > Math.abs(cur)) {
//                        stack.push(temp);
//                    } else if (temp < Math.abs(cur)) {
//                        judge(cur, stack);
//                    }
//                } else {
//                    stack.push(temp);
//                    stack.push(cur);
//                }
//            }
//        }
//    }
}
