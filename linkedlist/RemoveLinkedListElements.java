/* Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val, and return the new head.
 * 
 * https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1207/
 */

package linkedlist;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
      ListNode sentinel = new ListNode(0);
      sentinel.next = head;
      ListNode prev = sentinel, curr = head;
      
      while(curr != null) {
        if(curr.val == val) {
          prev.next = curr.next;
        } else {
          prev = curr;
        }
        curr = curr.next;
      }
      return sentinel.next;
    }
}
