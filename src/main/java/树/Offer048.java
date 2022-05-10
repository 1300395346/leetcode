package 树;

import struct.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class Offer048 {
    public static void main(String[] args) {
        Offer048 offer048 = new Offer048();
        String result = offer048.serialize(new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5))));
        TreeNode ans = offer048.deserialize(result);
        System.out.println(result);
        System.out.println(ans);
    }

    public String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        stringBuilder.append("[");
        stringBuilder.append(root.val).append(",");
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                stringBuilder.append(node.left.val).append(",");
                queue.add(node.left);
            } else {
                stringBuilder.append("null").append(",");
            }
            if (node.right != null) {
                stringBuilder.append(node.right.val).append(",");
                queue.add(node.right);
            } else {
                stringBuilder.append("null").append(",");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.equals("[]")) {
            return null;
        }
        String[] str = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        Queue<TreeNode> queue = new ArrayDeque<>();
        int index = 1;
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if (!str[index].equals("null")) {
                temp.left = new TreeNode(Integer.parseInt(str[index]));
                queue.offer(temp.left);
            }
            index++;
            if (!str[index].equals("null")) {
                temp.right = new TreeNode(Integer.parseInt(str[index]));
                queue.offer(temp.right);
            }
            index++;
        }
        return root;
    }

}
