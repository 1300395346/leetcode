# 剑指Offer 028.展平多级双向链表

多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。这些子列表也有可能会有一个或多个自己的子项，以此类推，生成多级数据结构。

给定位于列表第一级的头结点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有节点出现在单级双链表中。

2022/4/29 第一次刷

初见思路：遍历输入的多级链表，先获取该节点的值，然后获取其孩子节点，然后是下一节点。可以将该多级链表看成一个二叉树，每个节点是该二叉树的一个节点，其孩子节点是左节点，下一个节点是右节点，那么这题的答案就是先序遍历二叉树。

代码如下：

```java
class Solution {
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }
    public Node dfs(Node head) {
        Node cur = head;
        Node last = null;
        while (cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                Node childLast = dfs(cur.child);
                next = cur.next;
                cur.next = cur.child;
                cur.child.prev = cur;

                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }

                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }
}
```

结果：通过！！时间复杂度O(n)，空间复杂度O(n)
