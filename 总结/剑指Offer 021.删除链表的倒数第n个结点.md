# 剑指Offer 021.删除链表的倒数第n个结点

给定一个链表，删除链表的倒数第`n`个结点，并返回链表的头结点

2022/4/26 第一次刷

初见思路：删除倒数第n个结点，就是删除第len-n+1个结点，遍历到该节点，删除即可

代码如下：

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = listLength(head);
        if (n == len) {
            return head.next;
        }
        int i = 1;
        ListNode temp = head;
        while (i < len - n) {
            head = head.next;
            ++i;
        }
        head.next = head.next.next;
        return temp;
    }

    public int listLength(ListNode head) {
        int len = 0;
        while (head != null) {
            ++len;
            head = head.next;
        }
        return len;
    }
}
```

结果：通过！！ 时间复杂度O(n)
