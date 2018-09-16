package tree;

import linkedlist.ListNode;

/**
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class ReverseBinaryTree {

    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode next;
        ListNode pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

}
