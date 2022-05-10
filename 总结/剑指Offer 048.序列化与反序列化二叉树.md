# 剑指Offer 048.序列化与反序列化二叉树

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你序列化/反序列化算法执行逻辑，只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

2022/5/10 第一次刷

初见思路：层次遍历，将遍历到的节点值存入list中，遇到null则将null存入。反序列则根据list的每一个值将其反序列化成树。

代码如下：

```java
public class Codec {
    public String serialize(TreeNode root) {
        if (root == null){
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
```

结果：通过！
