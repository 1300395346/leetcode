package 链表;

import struct.ListNode;

public class Offer027 {
    public static void main(String[] args) {
        Offer027 offer027 = new Offer027();
        ListNode head = new ListNode(1, new ListNode(2));
        System.out.println(offer027.isPalindrome(head));
        head.show();
    }

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
