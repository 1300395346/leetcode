# 剑指Offer 026.重排链表

给定一个单链表`L`的头结点`head`，单链表`L`表示为：`L0 → L1 → … → Ln-1 → Ln`，请将其重新排列变为：`L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …`，不能只是单纯改变节点内部的值，而是需要实际的进行节点交换。

2022/4/28 第一次刷

初见思路：将链表节点加入list中，然后根据下标重新生成节点

代码如下：

```java
class Solution{
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        if (head == null) {
            return;
        }
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        int i = 0;
        int j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            ++i;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            --j;
        }
        list.get(i).next = null;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(n)

其他思路：如果能将链表分成两条，一条从链表开头到链表中点，一条从链表结尾到链表中点，然后将两个链表合并成一条。

代码如下：

```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode mid = getMid(head);
        ListNode start = head;
        ListNode end = mid.next;
        mid.next = null;
        end = reverseList(end);
        mergeList(start, end);
    }

    public ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {//注意fast指针一次走两个节点
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

    public void mergeList(ListNode start, ListNode end) {
        ListNode startTemp;
        ListNode endTemp;
        while (start != null && end != null) {
            startTemp = start.next;
            endTemp = end.next;
            start.next = end;
            start = startTemp;
            end.next = start;
            end = endTemp;
        }
    }
}
```

结果：通过！！！ 时间复杂度O(n)，空间复杂度O(1)
