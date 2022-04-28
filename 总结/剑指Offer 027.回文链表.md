# 剑指Offer 027.回文链表

给定一个链表的**头节点**`head`，请判断其是否为回文链表。如果一个链表是回文，那么链表节点序列从前往后和从后往前看是相同的。

2022/4/28 第一次刷

初见思路：获取链表中间节点，然后判断每个节点的值是否相同

```java
class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode mid = getMid(head);
        ListNode start = head;
        ListNode end = mid.next;
        mid.next = null;
        end = reverseList(end);
        while (start != null && end != null) {
            if (start.val != end.val) {
                return false;
            }
            start = start.next;
            end = end.next;
        }
        return true;
    }

    public ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
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

结果：通过！！ 时间复杂度O(n)，空间复杂度O(1)
