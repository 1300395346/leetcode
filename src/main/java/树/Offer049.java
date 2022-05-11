package 树;

import struct.TreeNode;

public class Offer049 {
    public static void main(String[] args) {
        Offer049 offer049 = new Offer049();
        int ans = offer049.sumNumbers(new TreeNode(1, new TreeNode(2), new TreeNode(3)));
        System.out.println(ans);
    }

    //DFS
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int preSum) {
        if (root == null) {
            return 0;
        }
        int sum = preSum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return sum;
        } else {
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }

    //BFS
//    public int sumNumbers(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        Queue<TreeNode> queue = new LinkedList<>();
//        Queue<Integer> num = new LinkedList<>();
//        num.add(root.val);
//        queue.add(root);
//        int result = 0;
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            int nums = num.poll();
//            if (node.left == null && node.right == null) {
//                result += nums;
//            } else {
//                if (node.left != null) {
//                    queue.add(node.left);
//                    num.offer(nums * 10 + node.left.val);
//                }
//                if (node.right != null) {
//                    queue.add(node.right);
//                    num.offer(nums * 10 + node.right.val);
//                }
//            }
//        }
//        return result;
//    }
}
