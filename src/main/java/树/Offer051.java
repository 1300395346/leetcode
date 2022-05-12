package 树;

import struct.TreeNode;

public class Offer051 {
    public static void main(String[] args) {
        Offer051 offer051 = new Offer051();
        int ans = offer051.maxPathSum(new TreeNode(-10, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7))));
        System.out.println(ans);
    }

    int ans = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        if (root == null) {
            return 0;
        }
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        ans = Math.max(ans, left + right + root.val);
        return root.val + Math.max(left, right);
    }

}
