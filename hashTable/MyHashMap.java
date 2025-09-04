/* Design a HashMap without using any built-in hash table libraries.

Implement the MyHashMap class:

MyHashMap() initializes the object with an empty map.
void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.

https://leetcode.com/explore/learn/card/hash-table/182/practical-applications/1140/
*/

package hashTable;

public class MyHashMap {
  private static class Node {
    int key, value;
    Node next;
    Node(int k, int v, Node n) { key = k; value = v; next = n; }
  }

  private Node[] buckets;        // table
  private int capacity;          // always a power of two
  private int size;              // number of pairs
  private static final double LOAD_FACTOR = 0.75;

  public MyHashMap() {
    capacity = 1024;
    buckets = new Node[capacity];
    size = 0;
  }
  
  public void put(int key, int value) {
    int idx = index(key);
    for (Node n = buckets[idx]; n != null; n = n.next) {
      if (n.key == key) {
        n.value = value;
        return;
      }
    }
    buckets[idx] = new Node(key, value, buckets[idx]);
    size++;
    if (size > capacity * LOAD_FACTOR) resize();
  }
  
  public int get(int key) {
    int idx = index(key);
    for (Node n = buckets[idx]; n != null; n = n.next) {
      if (n.key == key) return n.value;
    }
    return -1;
  }
  
  public void remove(int key) {
    int idx = index(key);
    Node cur = buckets[idx], prev = null;
    while (cur != null) {
      if (cur.key == key) {
        if(prev == null) buckets[idx] = cur.next;
        else prev.next = cur.next;
        size--;
        return;
      }
      prev = cur;
      cur = cur.next;
    }
  }

  //helpers

  private int index(int key) {
    int h = key ^ (key >>> 16);
    return (h & 0x7fffffff) & (capacity - 1);
  }

  private void resize() {
    int oldCap = capacity;
    Node[] old = buckets;

    capacity <<= 1;
    buckets = new Node[capacity];

    for (int i = 0; i < oldCap; i++) {
      Node n = old[i];
      while (n != null) {
        Node next = n.next;
        int idx = index(n.key);
        n.next = buckets[idx];
        buckets[idx] = n;
        n = next;
      }
    }
  }
}
