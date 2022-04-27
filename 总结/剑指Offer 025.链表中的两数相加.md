# 剑指Offer 025.链表中的两数相加

给定两个**非空链表**`l1`和`l2`来代表两个非负整数。数字最高位位于链表开始位置。他们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。

可以假设除了数字0以外，这两个数字都不会以零开头。

2022/4/27 初见

思路：反转链表，然后每个节点数值相加。

代码如下：

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode first = reverseList(l1);
        ListNode second = reverseList(l2);
        ListNode ans = new ListNode();
        int digit = 0;
        while (first != null || second != null || digit != 0) {
            if (first != null) {
                digit += first.val;
                first = first.next;
            }
            if (second != null) {
                digit += second.val;
                second = second.next;
            }
            ListNode node = new ListNode(digit % 10);
            node.next = ans.next;
            ans.next = node;
            digit = digit / 10;
        }
        return ans.next;
    }

    public ListNode reverseList(ListNode head) {
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

结果：通过！！ 时间复杂度O(max(m,n)+m+n)，空间复杂度O(1)，其中m为链表1的长度，n为链表2的长度。
