/* Design a HashSet without using any built-in hash table libraries.

Implement MyHashSet class:

void add(key) Inserts the value key into the HashSet.
bool contains(key) Returns whether the value key exists in the HashSet or not.
void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.

https://leetcode.com/explore/learn/card/hash-table/182/practical-applications/1139/
*/

package hashTable;

public class MyHashSet {
  private static class Node {
    int key;
    Node next;
    Node(int key, Node next) { this.key = key; this.next = next; }
  }

  private Node[] buckets;
  private int capacity;
  private int size;
  private final double LOAD_FACTOR = 0.75;

  public MyHashSet() {
    capacity = 1024;
    buckets = new Node[capacity];
    size = 0;
  }
  
  public void add(int key) {
    if (contains(key)) return;
    int idx = index(key);
    buckets[idx] = new Node(key, buckets[idx]);
    size++;
    if (size > capacity * LOAD_FACTOR) {
      resize();
    }
  }
  
  public void remove(int key) {
    int idx = index(key);
    Node curr = buckets[idx], prev = null;
    while (curr != null) {
      if (curr.key == key) {
        if (prev == null) buckets[idx] = curr.next;
        else prev.next = curr.next;
        size--;
        return;
      }
      prev = curr;
      curr = curr.next;
    }
  }
  
  public boolean contains(int key) {
    int idx = index(key);
    Node curr = buckets[idx];
    while (curr != null) {
      if (curr.key == key) return true;
      curr = curr.next;
    }
    return false;
  }

  // helpers

  private int index(int key) {
    int h = key ^ (key >>> 16);
    return (h & 0x7fffffff) & (capacity - 1);
  }

  private void resize() {
    int oldCap = capacity;
    Node[] oldBuckets = buckets;

    capacity <<= 1;
    buckets = new Node[capacity];

    for (int i = 0; i < oldCap; i++) {
      Node curr = oldBuckets[i];
      while (curr != null) {
        Node next = curr.next;
        int idx = index(curr.key);
        curr.next = buckets[idx];
        buckets[idx] = curr;
        curr = next;
      }
    }
  }
}
