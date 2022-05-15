package 树;

import struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Offer055 {
    private List<Integer> list;
    private int index;

    public Offer055(TreeNode root) {
        index = 0;
        list = new ArrayList<>();
        inorderTraversal(root, list);
    }

    public int next() {
        return list.get(index++);
    }

    public boolean hasNext() {
        return index < list.size();
    }

    private void inorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }
        inorderTraversal(node.left, list);
        list.add(node.val);
        inorderTraversal(node.right, list);
    }

}
