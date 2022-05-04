package 栈;

import java.util.Stack;

public class Offer036 {
    public static void main(String[] args) {
        Offer036 offer036 = new Offer036();
        int ans = offer036.evalRPN(new String[]{"4", "13", "5", "/", "+"});
        System.out.println(ans);
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            if (isNumber(s)) {
                stack.push(Integer.parseInt(s));
            } else {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(isOperator(a, b, s));
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String s) {
        return !("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s));
    }

    public int isOperator(int a, int b, String s) {
        int ans = 0;
        switch (s) {
            case "+":
                ans = a + b;
                break;
            case "-":
                ans = b - a;
                break;
            case "*":
                ans = a * b;
                break;
            case "/":
                ans = b / a;
                break;
            default:
        }
        return ans;
    }
}
