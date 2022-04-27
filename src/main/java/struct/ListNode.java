package struct;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int val) {
        this.val = val;
        next = null;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode() {
        
    }

    public void show() {
        ListNode currentNode = this;
        while (true) {
            if (currentNode == null) {
                System.out.println();
                break;
            }
            System.out.print(currentNode.val + " ");
            currentNode = currentNode.next;
        }
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
