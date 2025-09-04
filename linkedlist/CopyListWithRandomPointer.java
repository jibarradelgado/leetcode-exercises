/* A linked list of length n is given such that each node contains an additional random pointer, which could point to any node in the list, or null.

Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes should point to new nodes in the copied list such that the pointers in the original list and copied list represent the same list state. None of the pointers in the new list should point to nodes in the original list.

For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding two nodes x and y in the copied list, x.random --> y.

Return the head of the copied linked list.

The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair of [val, random_index] where:

val: an integer representing Node.val
random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not point to any node.
Your code will only be given the head of the original linked list.

https://leetcode.com/explore/learn/card/linked-list/213/conclusion/1229/
*/

package linkedlist;

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/
public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
      if (head == null) {
        return null;
      }

      Node ptr = head;
      while (ptr != null) {
        Node newNode = new Node(ptr.val);

        newNode.next = ptr.next;
        ptr.next = newNode;
        ptr = newNode.next;
      }

      ptr = head;

      while (ptr != null) {
        ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
        ptr = ptr.next.next;
      }

      Node ptr_old_list = head;
      Node ptr_new_list = head.next;
      Node head_new = head.next;
      while (ptr_old_list != null) {
        ptr_old_list.next = ptr_old_list.next.next;
        ptr_new_list.next = (ptr_new_list.next != null)
          ? ptr_new_list.next.next
          : null;
        ptr_old_list = ptr_old_list.next;
        ptr_new_list = ptr_new_list.next;
      }
      return head_new;
    }
}
