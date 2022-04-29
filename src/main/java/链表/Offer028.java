package 链表;

import struct.Node;

public class Offer028 {
    public static void main(String[] args) {
        Offer028 offer028 = new Offer028();
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        n1.setAll(null, n2, n3);
        n2.setAll(n1, null, null);
        n3.setAll(null, null, null);
        Node ans = offer028.flatten(n1);
        ans.show();
    }

    public Node flatten(Node head) {
        dfs(head);
        return head;
    }

    public Node dfs(Node head) {
        Node cur = head;
        Node last = null;
        while (cur != null) {
            Node next = cur.next;
            if (cur.child != null) {
                Node childLast = dfs(cur.child);
                next = cur.next;
                cur.next = cur.child;
                cur.child.prev = cur;

                if (next != null) {
                    childLast.next = next;
                    next.prev = childLast;
                }

                cur.child = null;
                last = childLast;
            } else {
                last = cur;
            }
            cur = next;
        }
        return last;
    }

}
