package 树;

import struct.TreeNode;

public class Offer045 {
    public static void main(String[] args) {
        Offer045 offer045 = new Offer045();
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        System.out.println(offer045.findBottomLeftValue(root));
    }

    int height = 0;
    TreeNode max = null;

    //DFS
    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 1);
        return max.val;
    }

    public void dfs(TreeNode root, int h) {
        if (root == null) {
            return;
        }
        if (h > height) {
            height = h;
            max = root;
        }
        dfs(root.left, h + 1);
        dfs(root.right, h + 1);
    }

    //BFS
//    public int findBottomLeftValue(TreeNode root) {
//        int ans = root.val;
//        Queue<TreeNode> queue = new ArrayDeque<>();
//        queue.add(root);
//        int count = queue.size();
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            count--;
//            if (node.left != null) {
//                queue.add(node.left);
//            }
//            if (node.right != null) {
//                queue.add(node.right);
//            }
//            if (count == 0) {
//                count = queue.size();
//                ans = queue.isEmpty() ? ans : queue.peek().val;
//            }
//        }
//        return ans;
//    }
}
