package 树;

import struct.TreeNode;

public class Offer053 {
    public static void main(String[] args) {
        Offer053 offer053 = new Offer053();
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        TreeNode ans = offer053.inorderSuccessor(root, new TreeNode(1));
        System.out.println(ans.val);
    }

    //利用二叉搜索树的性质
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode ans = null;
        if (p.right != null) {
            ans = p.right;
            while (ans.left != null) {
                ans = ans.left;
            }
            return ans;
        }
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val) {
                ans = node;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return ans;
    }

    //利用栈实现中序遍历
//    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//        Deque<TreeNode> stack = new ArrayDeque<>();
//        TreeNode prev = null, curr = root;
//        while (!stack.isEmpty() || curr != null) {
//            while (curr != null) {
//                stack.push(curr);
//                curr = curr.left;
//            }
//            curr = stack.pop();
//            if (prev == p) {
//                return curr;
//            }
//            prev = curr;
//            curr = curr.right;
//        }
//        return null;
//    }

    //DFS
//    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
//        List<TreeNode> list = new ArrayList<>();
//        dfs(root, list);
//        if (list.contains(p)) {
//            int index = list.indexOf(p);
//            for (int i = index; i < list.size(); ++i) {
//                if (list.get(i).val > p.val) {
//                    return list.get(i);
//                }
//            }
//        }
//        return null;
//    }
//
//    public void dfs(TreeNode node, List<TreeNode> list) {
//        if (node == null) {
//            return;
//        }
//        dfs(node.left, list);
//        list.add(node);
//        dfs(node.right, list);
//    }
}
