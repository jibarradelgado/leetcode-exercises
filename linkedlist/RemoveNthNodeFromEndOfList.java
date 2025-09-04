/* Given the head of a linked list, remove the nth node from the end of the list and return its head.
 * 
 * https://leetcode.com/explore/learn/card/linked-list/214/two-pointer-technique/1296/
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
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
      ListNode dummy = new ListNode(0);
      dummy.next = head;
      ListNode first = dummy;
      ListNode second = dummy;
      for (int i = 1; i <= n + 1; i++) {
        first = first.next;
      }
      while (first != null) {
        first = first.next;
        second = second.next;
      }
      second.next = second.next.next;
      return dummy.next;
    }
}
