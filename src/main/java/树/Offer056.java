package 树;

import struct.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Offer056 {
    public static void main(String[] args) {
        Offer056 offer056 = new Offer056();
        boolean ans = offer056.findTarget(new TreeNode(8, new TreeNode(6, new TreeNode(5), new TreeNode(7)), new TreeNode(10, new TreeNode(9), new TreeNode(11))), 22);
        System.out.println(ans);
    }

    Map<Integer, TreeNode> map;

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        map = new HashMap<>();
        dfs(root, 12);
        for (Map.Entry<Integer, TreeNode> entry : map.entrySet()) {
            int temp = entry.getKey();
            if (map.containsKey(k - temp) && k - temp != temp) {
                return true;
            }
        }
        return false;
    }

    public void dfs(TreeNode node, int target) {
        if (node == null) {
            return;
        }
        dfs(node.left, target);
        map.put(node.val, node);
        dfs(node.right, target);
    }
}
