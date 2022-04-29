package 链表;

import struct.CircleNode;

public class Offer029 {
    public static void main(String[] args) {
        Offer029 offer029 = new Offer029();
        CircleNode n1 = new CircleNode(1);
        CircleNode n2 = new CircleNode(4, n1);
        CircleNode head = new CircleNode(3, n2);
        n1.next = head;
        CircleNode ans = offer029.insert(head, 2);
        ans.show();
    }

    public CircleNode insert(CircleNode head, int insertVal) {
        if (head == null) {
            head = new CircleNode(insertVal);
            head.next = head;
            return head;
        }

        CircleNode cur = head;
        CircleNode next = head.next;

        while (cur.val <= next.val) {
            cur = cur.next;
            next = next.next;
            if (cur == head) {
                break;
            }
        }
        CircleNode realHead = next;
        while (next.val < insertVal) {
            cur = next;
            next = next.next;
            if (next == realHead) {
                break;
            }
        }

        cur.next = new CircleNode(insertVal);
        cur = cur.next;
        cur.next = next;
        return head;
    }
}
