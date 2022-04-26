# 剑指Offer 022.链表中环的入口节点

给定一个链表，返回链表开始入环的第一个节点。从链表的头结点开始沿着`next`指针进入环的第一个节点为环的入口节点。如果链表无环，则返回`null`。

为了表示给定链表中的环，我们使用整数`pos`来表示链表尾连接到链表中的位置（索引从0开始）。如果`pos`是`-1`，则在该链表中没有环。**注意：`pos`仅仅是用于标识环的情况，并不会作为参数传递到函数中。**

**说明**：不允许修改链表。

2022/4/26 第一次刷

初见思路：将链表的节点存入Set集合当中，若集合中存在该节点，返回该节点，否则将该节点存入集合，若节点为空，则不存在循环节点，返回null

代码如下：

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return null;
    }
}
```

结果：通过！ 时间复杂度O(n)，空间复杂度O(n)

进阶：令空间复杂度为O(1)

思路：要令空间复杂度为O(1)，意味着辅助空间是个常数，因此只能考虑指针。（往后就没有思路了，参考了官方题解：快慢指针）

假设一个环形链表，从开头到环形入口节点长度为a，快指针每次向前移动两个节点，慢指针每次向前移动一个节点。因为存在环形链表，快慢指针必然在环形链表内相遇，由于快指针的速度是慢指针的两倍，所以当慢指针走过环形链表一圈时，快指针必然走过了环形链表两圈，所以两个指针第一次相遇时慢指针必然没有走过一圈。假设快慢指针相遇时，慢指针走过的距离为a+b，快指针走过的距离为a+n×(b+c)+b=a+(n+1)×b+n×c，其中n表示快慢指针相遇是快指针走过的圈数（当环形链表长度足够小时，快慢指针相遇是快指针是可以走的超过一圈的，因此n不确定）。同时因为快指针每次经过两个节点，慢指针每次只经过一个节点，因此快指针的速度是慢指针的两倍，故2×(a+b) = a+(n+1)×b+n×c  => a = (n-1)×(b+c)+c。即慢指针从相遇点开始走到环形链表的起始点然后开始绕环形链表走n-1圈的距离，恰好是从链表起点到环形链表起点的距离。这就意味着，当快指针和慢指针相遇时，第三个指针从起点出发，这一个指针和慢指针相遇的节点正好是环形链表的起点。

代码如下：

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        if (head == null) {
            return null;
        }
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (fast == slow) {
                ListNode ans = head;
                while (ans != slow) {
                    slow = slow.next;
                    ans = ans.next;
                }
                return ans;
            }
        }
        return null;
    }
}
```

结果：通过！！ 时间复杂度O(n)，空间复杂度O(1)

**注意**：链表长度可能为1或无循环链表且fast恰好指向链表尾部时，不存在fast.next.next；因此需要从fast是否为空开始判断，让慢指针先走一格，随后判断链表长度不为1，在令快指针走两格，若此时快指针已经为空，会在下一次循环跳出。


