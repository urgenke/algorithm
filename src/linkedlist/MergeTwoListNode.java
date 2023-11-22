package linkedlist;

import base.ListNode;

/**
 * @author : mocun
 * @since : 2023/10/24
 */
public class MergeTwoListNode {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode i = list1;
        ListNode j = list2;
        ListNode nodeToUse = new ListNode(0);
        ListNode current = nodeToUse;

        while(i == null && j== null){
            if(i.val <= j.val){
                nodeToUse.next = i;
                i = i.next;

            }else{
                nodeToUse.next = j;
                j = j.next;
            }
            current = nodeToUse.next;
        }
        return nodeToUse.next;
    }


}
