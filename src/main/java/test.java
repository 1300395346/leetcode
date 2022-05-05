import java.util.ArrayDeque;
import java.util.Deque;

public class test {
    public int[] next(int[] test) {
        Deque<Integer> stack = new ArrayDeque<>();
        int len = test.length;
        int[] ans = new int[len];
        for (int i = len - 1; i >= 0; --i) {
            while (!stack.isEmpty() && stack.peek() <= test[i]) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(test[i]);
        }
        return ans;
    }
}
