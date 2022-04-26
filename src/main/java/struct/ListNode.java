package struct;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public void show() {
        ListNode currentNode = this;
        while (true) {
            System.out.print(currentNode.val + " ");
            currentNode = currentNode.next;
            if (currentNode == null) {
                System.out.println();
                break;
            }
        }
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
