/* Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle, and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

One of the benefits of the circular queue is that we can make use of the spaces in front of the queue. In a normal queue, once the queue becomes full, we cannot insert the next element even if there is a space in front of the queue. But using the circular queue, we can use the space to store new values.

Implement the MyCircularQueue class:

MyCircularQueue(k) Initializes the object with the size of the queue to be k.
int Front() Gets the front item from the queue. If the queue is empty, return -1.
int Rear() Gets the last item from the queue. If the queue is empty, return -1.
boolean enQueue(int value) Inserts an element into the circular queue. Return true if the operation is successful.
boolean deQueue() Deletes an element from the circular queue. Return true if the operation is successful.
boolean isEmpty() Checks whether the circular queue is empty or not.
boolean isFull() Checks whether the circular queue is full or not.
You must solve the problem without using the built-in queue data structure in your programming language. 

https://leetcode.com/explore/learn/card/queue-stack/228/first-in-first-out-data-structure/1337/
*/

package queueAndStack;

public class MyCircularQueue {
  private final int[] data;
  private final int cap;
  private int head;
  private int count;

  public MyCircularQueue(int k) {
    this.data = new int[k];
    this.cap = k;
    this.head = 0;
    this.count = 0;
  }

  public boolean enQueue(int value) {
    if (isFull()) return false;
    int tail = (head + count) % cap;
    data[tail] = value;
    count++;
    return true;
  }

  public boolean deQueue() {
    if (isEmpty()) return false;
    head = (head + 1) % cap;
    count--;
    return true;
  }

  public int Front() {
    if(isEmpty()) return -1;
    return data[head];
  }

  public int Rear() {
    if (isEmpty()) return -1;
    int tailIdx = (head + count - 1) % cap;
    return data[tailIdx];
  }

  public boolean isEmpty() {
    return count == 0;
  } 

  public boolean isFull() {
    return count == cap;
  }
}
