package struct;

public class CircleNode {
    public int val;
    public CircleNode next;

    public CircleNode() {
    }

    public CircleNode(int _val) {
        val = _val;
    }

    public CircleNode(int _val, CircleNode _next) {
        val = _val;
        next = _next;
    }

    public void show() {
        CircleNode head = this;
        CircleNode cur = head;
        while (true) {
            System.out.print(cur.val + " ");
            if (cur.next == head) {
                System.out.println();
                break;
            }
            cur = cur.next;
        }
    }
}
