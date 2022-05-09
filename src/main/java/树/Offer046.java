package 树;

import struct.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Offer046 {
    public static void main(String[] args) {
        Offer046 offer046 = new Offer046();
        List<Integer> ans = offer046.rightSideView(new TreeNode(1, null, new TreeNode(3)));
        System.out.println(ans);
    }

    //DFS
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<Integer> ans = new ArrayList<>();
        dfs(root, 0, ans);
        return ans;
    }

    public void dfs(TreeNode root, int depth, List<Integer> ans) {
        if (root == null) {
            return;
        }
        if (depth == ans.size()) {
            ans.add(0);
        }
        ans.set(depth++, root.val);
        dfs(root.left, depth, ans);
        dfs(root.right, depth, ans);
    }

    //BFS
//    public List<Integer> rightSideView(TreeNode root) {
//        List<Integer> ans = new ArrayList<>();
//        Queue<TreeNode> queue = new ArrayDeque<>();
//        if (root == null) {
//            return Collections.emptyList();
//        }
//        queue.add(root);
//        int temp = queue.size();
//        int result;
//        while (!queue.isEmpty()) {
//            TreeNode node = queue.poll();
//            temp--;
//            result = node.val;
//            if (node.left != null) {
//                queue.add(node.left);
//            }
//            if (node.right != null) {
//                queue.add(node.right);
//            }
//            if (temp == 0) {
//                temp = queue.size();
//                ans.add(result);
//            }
//        }
//        return ans;
//    }

}
