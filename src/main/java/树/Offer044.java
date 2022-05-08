package 树;

import struct.TreeNode;

import java.util.*;

public class Offer044 {
    public static void main(String[] args) {
        Offer044 offer044 = new Offer044();
        TreeNode root = null;
        List<Integer> ans = offer044.largestValues(root);
        System.out.println(ans.toString());
    }

    public List<Integer> largestValues(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return Collections.emptyList();
        }
        queue.add(root);
        int max = Integer.MIN_VALUE;
        int count = queue.size();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count--;
            max = Math.max(max, node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            if (count == 0) {
                count = queue.size();
                ans.add(max);
                max = Integer.MIN_VALUE;
            }
        }
        return ans;
    }
}
