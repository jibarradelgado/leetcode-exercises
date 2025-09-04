/* Given the head of a singly linked list, return true if it is a palindrome or false otherwise.
 * 
 * https://leetcode.com/explore/learn/card/linked-list/219/classic-problems/1209/
 */

package linkedlist;

import java.util.ArrayList;
import java.util.List;

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
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
      List<Integer> vals = new ArrayList<>();

      ListNode currentNode = head;
      while(currentNode != null) {
        vals.add(currentNode.val);
        currentNode = currentNode.next;
      }

      int front = 0;
      int back = vals.size() - 1;
      while (front < back) {
        if (!vals.get(front).equals(vals.get(back))) {
          return false;
        }
        front++;
        back--;
      }
      return true;
    }
}
