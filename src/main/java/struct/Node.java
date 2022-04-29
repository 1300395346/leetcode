package struct;

public class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node prev, Node next, Node child) {
        this.val = val;
        this.prev = prev;
        this.next = next;
        this.child = child;
    }

    public void setAll(Node prev, Node next, Node child) {
        this.prev = prev;
        this.next = next;
        this.child = child;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setChild(Node child) {
        this.child = child;
    }

    public void show() {
        Node currentNode = this;
        while (true) {
            if (currentNode == null) {
                System.out.println();
                break;
            }
            System.out.print(currentNode.val + " ");
            currentNode = currentNode.next;
        }
    }
}
