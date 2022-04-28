package 链表;

import struct.ListNode;

public class Offer026 {
    public static void main(String[] args) {
        Offer026 offer026 = new Offer026();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        offer026.reorderList(head);
        head.show();
    }

    //分割链表
    public void reorderList(ListNode head) {
        ListNode mid = getMid(head);
        ListNode start = head;
        ListNode end = mid.next;
        mid.next = null;
        end = reverseList(end);
        mergeList(start, end);
    }

    public ListNode getMid(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {//注意fast指针一次走两个节点
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

    public void mergeList(ListNode start, ListNode end) {
        ListNode startTemp;
        ListNode endTemp;
        while (start != null && end != null) {
            startTemp = start.next;
            endTemp = end.next;
            start.next = end;
            start = startTemp;
            end.next = start;
            end = endTemp;
        }
    }
    //链表+list
//    public void reorderList(ListNode head) {
//        List<ListNode> list = new ArrayList<>();
//        if (head == null) {
//            return;
//        }
//        while (head != null) {
//            list.add(head);
//            head = head.next;
//        }
//        int i = 0;
//        int j = list.size() - 1;
//        while (i < j) {
//            list.get(i).next = list.get(j);
//            ++i;
//            if (i == j) {
//                break;
//            }
//            list.get(j).next = list.get(i);
//            --j;
//        }
//        list.get(i).next = null;
//    }
}
