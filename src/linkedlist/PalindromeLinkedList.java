package linkedlist;

import base.ListNode;

/**
 * Date: 2018/9/16 下午2:13
 * Author: qianhangkang
 * Description: 判断一个链表是否为回文结构(一个正读和反读都一样的字符串，example:aaaa,aacaa)，要求空间复杂度为O(1),时间复杂度为O(n)
 */
public class PalindromeLinkedList {
    /**
     * 1. 快指针满指针找到链表中间的位置
     * 2. 反转后半部分的链表
     * 3. 比较对应字符是否相等
     */

    public boolean judgePalindromeLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode fast = head;//快指针，一次前进两步
        ListNode slow = head;//慢指针，一次前进一步
        while (fast.next != null) {
            fast = fast.next;
            if (fast.next != null) {
                fast = fast.next;
                slow = slow.next;
            }

        }
        //slow处于中间位置
        /*
            a->b->b<-a

            a->b->c<-b<-a

         */
        //从slow开始翻转
        ListNode next;
        ListNode pre = null;
        while (slow != null) {
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }
        while (head != null && pre != null) {
            if (head.val == pre.val) {
                head = head.next;
                pre = pre.next;
            } else {
                return false;
            }
        }
        return true;

    }


    public static void main(String[] args) {
        PalindromeLinkedList test = new PalindromeLinkedList();
        ListNode node = new ListNode(1);
        node.next = new ListNode(2);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(2);
        node.next.next.next.next = new ListNode(1);
        System.out.println(test.judgePalindromeLinkedList(node));
    }
}
