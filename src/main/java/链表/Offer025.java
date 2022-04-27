package 链表;

import struct.ListNode;

public class Offer025 {
    public static void main(String[] args) {
        Offer025 offer025 = new Offer025();
        ListNode l1 = new ListNode(7, new ListNode(2, new ListNode(4, new ListNode(3))));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        ListNode ans = offer025.addTwoNumbers(l1, l2);
        ans.show();
    }

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
