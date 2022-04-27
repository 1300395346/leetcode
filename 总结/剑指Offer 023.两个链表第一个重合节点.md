# 剑指Offer 023.两个链表第一个重合节点

给定两个链表的头结点`headA`和`headB`，请找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回`null`。

题目数据**保证**整个链式结构中不存在环。函数返回结果后，链表必须**保持其原始结构**。

2022/4/27 第一次刷

初见思路：遍历一遍链表A，将其节点存入哈希表中，在遍历一遍链表B，判断其节点是否在哈希表中，若存在，则该节点是就是两个单链表相交的第一个节点，若无则返回null。

代码如下：

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        if (headA == null || headB == null) {
            return null;
        }
        while (headA != null) {
            set.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}
```

结果：通过！！ 时间复杂度O(m+n)，空间复杂度O(m)，m为链表A的长度，n为链表B的长度。

进阶：时间复杂度为O(n)，空间复杂度为O(1)

思路：要求空间复杂度为常量，说明需要采用指针的方式遍历链表，两个链表应该需要两个指针，但是如何判断相遇节点？参考上一题快慢指针解法，这一题需要让两个指针一相同的速度前进，指针p指向链表A的头部，指针q指向链表B的头部，当p指向链表A尾部时，将p跳转到到链表B的头部，指针p同理。

那么当指针p和指针q相遇的时候有两种情况：

1. 链表A和链表B的非公共部分，a和b长度相同，那么指针会在链表A和链表B的公共部分的开头相遇

2. 链表A和链表B的非公共部分，a好b长度不同，那么指针p和q不会同时到达链表尾部，若c表示链表公共部分长度，那么指针p走过a+c个节点后会指向链表B的头结点，指针q走过b+c个节点后会指向链表A的头结点。此时指针p再走过b个节点后，指针q也会走过a个节点，两个节点走过的距离相等，因此相遇，同时相遇节点为共同链表的起始节点。

代码如下：

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;
        if (p == null || q == null) {
            return null;
        }
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
```

结果：通过！！！ 时间复杂度O(n+m)，空间复杂度O(1)
