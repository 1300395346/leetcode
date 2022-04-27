# 剑指Offer 024.反转链表

给定单链表的头结点`head`，请反转链表，并返回反转后链表的头结点。

2022/4/27 第一次刷

思路：一个指针pre为空，一个指针cur指向当前节点，然后申请一个临时指针temp指向当前节点的下一节点，令cur.next = pre，pre = cur，cur = temp；

代码如下：

```java
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode ans = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = ans;
            ans = cur;
            cur = temp;
        }
        return ans;
    }
}
```

结果：通过！ 时间复杂度O(n)，空间复杂度O(1)
