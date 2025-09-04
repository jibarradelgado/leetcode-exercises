/* Design your implementation of the linked list. You can choose to use a singly or doubly linked list.
A node in a singly linked list should have two attributes: val and next. val is the value of the current node, and next is a pointer/reference to the next node.
If you want to use the doubly linked list, you will need one more attribute prev to indicate the previous node in the linked list. Assume all nodes in the linked list are 0-indexed.

Implement the MyLinkedList class:

MyLinkedList() Initializes the MyLinkedList object.
int get(int index) Get the value of the indexth node in the linked list. If the index is invalid, return -1.
void addAtHead(int val) Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
void addAtTail(int val) Append a node of value val as the last element of the linked list.
void addAtIndex(int index, int val) Add a node of value val before the indexth node in the linked list. If index equals the length of the linked list, the node will be appended to the end of the linked list. If index is greater than the length, the node will not be inserted.
void deleteAtIndex(int index) Delete the indexth node in the linked list, if the index is valid.

https://leetcode.com/explore/learn/card/linked-list/209/singly-linked-list/1290/
*/

package linkedlist;

public class DesignLinkedList {
    private static class Node {
        int val;
        Node next, prev;
        Node(int v) { val = v; }
    }

    private Node head, tail;
    private int size;

    public DesignLinkedList() {
        head = tail = null;
        size = 0;
    }

    public int get(int index) {
        Node n = nodeAt(index);
        return n == null ? -1 : n.val;
    }

    public void addAtHead(int val) {
        Node n = new Node(val);
        n.next = head;
        if (head != null) head.prev = n;
        head = n;
        if (tail == null) tail = n;
        size++;
    }

    public void addAtTail(int val) {
        Node n = new Node(val);
        n.prev = tail;
        if (tail != null) tail.next = n;
        tail = n;
        if (head == null) head = n;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index < 0 || index > size) return;

        if (index == 0) { addAtHead(val); return; }
        if (index == size) { addAtTail(val); return; }

        Node curr = nodeAt(index);        // must be the node currently at 'index'
        if (curr == null) return;         // defensive (shouldn't happen if bounds ok)

        Node prev = curr.prev;
        Node n = new Node(val);

        // link: prev <-> n <-> curr
        n.prev = prev;
        n.next = curr;
        prev.next = n;
        curr.prev = n;

        size++;
    }

    public void deleteAtIndex(int index) {
        Node curr = nodeAt(index);
        if (curr == null) return;

        Node p = curr.prev, q = curr.next;

        if (p != null) p.next = q; else head = q;
        if (q != null) q.prev = p; else tail = p;

        size--;
    }

    // -------- helpers --------
    private Node nodeAt(int index) {
        if (index < 0 || index >= size) return null;
        if (index <= size / 2) {
            Node x = head;
            for (int i = 0; i < index; i++) x = x.next;
            return x;
        } else {
            Node x = tail;
            for (int i = size - 1; i > index; i--) x = x.prev; // NOTE: '>' not '>='
            return x;
        }
    }
}
