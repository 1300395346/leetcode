package 树;

import struct.TreeNode;

public class Offer052 {
    public static void main(String[] args) {
        Offer052 offer052 = new Offer052();
        TreeNode root = new TreeNode(5, new TreeNode(1), new TreeNode(7));
        System.out.println(root.toString());
        TreeNode ans = offer052.increasingBST(root);
        System.out.println(ans);
    }

    private TreeNode resNode;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(-1);
        resNode = dummyNode;

        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        inorder(node.left);
        resNode.right = node;
        node.left = null;
        resNode = node;
        inorder(node.right);
    }

//    public TreeNode increasingBST(TreeNode root) {
//        List<Integer> list = new ArrayList<>();
//        inorder(root, list);
//
//        TreeNode dummyNode = new TreeNode(-1);
//        TreeNode curr = dummyNode;
//        for (int value : list) {
//            curr.right = new TreeNode(value);
//            curr = curr.right;
//        }
//        return dummyNode.right;
//    }
//
//    public void inorder(TreeNode root, List<Integer> list) {
//        if (root == null) {
//            return;
//        }
//        inorder(root.left, list);
//        list.add(root.val);
//        inorder(root.right, list);
//    }
}
