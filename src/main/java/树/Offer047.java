package 树;

import struct.TreeNode;

public class Offer047 {
    public static void main(String[] args) {
        Offer047 offer047 = new Offer047();
        TreeNode ans = offer047.pruneTree(new TreeNode(1, new TreeNode(0, new TreeNode(0), new TreeNode(0)), new TreeNode(1, new TreeNode(0), new TreeNode(1))));
        System.out.println(ans.toString());
    }

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }
}
