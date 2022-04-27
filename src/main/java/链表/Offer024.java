package 链表;

import struct.ListNode;

public class Offer024 {
    public static void main(String[] args) {
        Offer024 offer024 = new Offer024();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        ListNode ans = offer024.reverseList(head);
        ans.show();
    }

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
