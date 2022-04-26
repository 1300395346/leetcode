package 链表;

import struct.ListNode;

public class Offer022 {
    public static void main(String[] args) {
        Offer022 offer022 = new Offer022();
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(2);

        head.setNext(n1);
        n1.setNext(head);
        ListNode ans = offer022.detectCycle(head);
        System.out.println(ans.val);
    }

    //快慢指针
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

    //哈希表
//    public ListNode detectCycle(ListNode head) {
//        Set<ListNode> set = new HashSet<>();
//        while (head != null) {
//            if (set.contains(head)) {
//                return head;
//            } else {
//                set.add(head);
//            }
//            head = head.next;
//        }
//        return null;
//    }
}
