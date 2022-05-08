package 树;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Offer043 {
    public static void main(String[] args) {
        Offer043 offer043 = new Offer043(TreeNode.initBinaryTree(1, 2, 3, 4, 5, 6));
        System.out.println(offer043.insert(7));
        System.out.println(offer043.insert(8));
        System.out.println(offer043.get_root());
    }


    private final Queue<TreeNode> queue;
    private final TreeNode root;
    private boolean isLeft;

    public Offer043(TreeNode root) {
        this.root = root;
        queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.peek();
            if (node.left == null) {
                isLeft = true;
                break;
            } else {
                queue.add(node.left);
            }
            if (node.right == null) {
                isLeft = false;
                break;
            } else {
                queue.add(node.right);
                queue.poll();
            }
        }
    }

    public int insert(int v) {
        TreeNode father;
        TreeNode child = new TreeNode(v);
        if (isLeft) {
            father = queue.peek();
            father.left = child;
        } else {
            father = queue.poll();
            father.right = child;
        }
        queue.add(child);
        isLeft = !isLeft;
        return father.val;
    }

    public TreeNode get_root() {
        return root;
    }
}
