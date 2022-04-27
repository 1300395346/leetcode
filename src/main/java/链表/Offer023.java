package 链表;

import struct.ListNode;

public class Offer023 {
    public static void main(String[] args) {
        Offer023 offer023 = new Offer023();
        ListNode headA = new ListNode(4);
        ListNode headB = new ListNode(5);
        ListNode a1 = new ListNode(1);
        ListNode b1 = new ListNode(0);
        ListNode b2 = new ListNode(1);
        ListNode c1 = new ListNode(8);
        ListNode c2 = new ListNode(4);
        ListNode c3 = new ListNode(5);
        c2.setNext(c3);
        c1.setNext(c2);
        a1.setNext(c1);
        headA.setNext(a1);
        b2.setNext(c1);
        b1.setNext(b2);
        headB.setNext(b1);
        ListNode ans = offer023.getIntersectionNode(headA, headB);
        ans.show();
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;
        if (p == null || q == null) {
            return null;
        }
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
}
