package 链表;

import struct.ListNode;

public class Offer021 {
    public static void main(String[] args) {
        Offer021 offer021 = new Offer021();
        ListNode n4 = new ListNode(5);
        ListNode n3 = new ListNode(4, n4);
        ListNode n2 = new ListNode(3, n3);
        ListNode n1 = new ListNode(2, n2);
        ListNode head = new ListNode(1, n1);
        head.show();
        ListNode ans = offer021.removeNthFromEnd(head, 5);
        ans.show();
    }

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
