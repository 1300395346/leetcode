package 树;

import struct.TreeNode;

public class Offer054 {
    public static void main(String[] args) {
        Offer054 offer054 = new Offer054();
        TreeNode ans = offer054.convertBST(new TreeNode(4, new TreeNode(1, new TreeNode(0), new TreeNode(2, null, new TreeNode(3))), new TreeNode(6, new TreeNode(5), new TreeNode(7, null, new TreeNode(8)))));
        System.out.println(ans);
    }

    int sum = 0;

    //Morris遍历
    public TreeNode convertBST(TreeNode root) {
        TreeNode node = root;

        while (node != null) {
            if (node.right == null) {
                sum += node.val;
                node.val = sum;
                node = node.left;
            } else {
                TreeNode curr = getLeftNode(node);
                if (curr.left == null) {
                    curr.left = node;
                    node = node.right;
                } else {
                    curr.left = null;
                    sum += node.val;
                    node.val = sum;
                    node = node.left;
                }
            }
        }

        return root;
    }

    public TreeNode getLeftNode(TreeNode node) {
        TreeNode temp = node.right;
        while (temp.left != null && temp.left != node) {
            temp = temp.left;
        }
        return temp;
    }
    //反序中序遍历
//
//    public TreeNode convertBST(TreeNode root) {
//        if (root != null) {
//            convertBST(root.right);
//            sum += root.val;
//            root.val = sum;
//            convertBST(root.left);
//        }
//        return root;
//    }
}
