# 剑指Offer 029.排序的循环链表

给定**循环单调非递减列表**中的一个点，写一个函数想这个列表中插入一个新元素`insertVal`，使这个列表仍然是循环升序的。

给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。

如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。

如果列表为空（给定的节点是`null`），需要创建一个循环有序的列表并返回这个节点。否则，请返回原先给定的节点。

2022/4/29 第一次刷

初见思路：找到循环链表中最小的节点，然后从这个节点开始找插入的点

代码如下：

```java
class Solution{
        public CircleNode insert(CircleNode head, int insertVal) {
        if (head == null) {
            head = new CircleNode(insertVal);
            head.next = head;
            return head;
        }

        CircleNode cur = head;
        CircleNode next = head.next;

        while (cur.val <= next.val) {
            cur = cur.next;
            next = next.next;
            if (cur == head) {
                break;
            }
        }
        CircleNode realHead = next;
        while (next.val < insertVal) {
            cur = next;
            next = next.next;
            if (next == realHead) {
                break;
            }
        }

        cur.next = new CircleNode(insertVal);
        cur = cur.next;
        cur.next = next;
        return head;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(1)
