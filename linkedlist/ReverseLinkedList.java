/* 
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 * 
 * https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1205/
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
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
      ListNode prev = null;
      ListNode curr = head;
      
      while(curr != null) {
        ListNode nextTemp = curr.next;  // store next node
        curr.next = prev;               // reverse the pointer
        prev = curr;                    // move prev to curr
        curr = nextTemp;                // move curr to next
      }
      
      return prev;
    }
}
