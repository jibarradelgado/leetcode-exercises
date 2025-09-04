/* Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * 
 * 
 * https://leetcode.com/explore/learn/card/recursion-i/250/principle-of-recursion/1681/
 */

package recursionI;

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
public class SwapNodesInParis {
    public ListNode swapPairs(ListNode head) {
      if (head == null || head.next == null) {
        return head; 
      }
      
      ListNode firstNode = head;
      ListNode secondNode = head.next;

      firstNode.next = swapPairs(secondNode.next);
      secondNode.next = firstNode;
      
      return secondNode;
    }
}
