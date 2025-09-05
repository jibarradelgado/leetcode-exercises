/* Implement a first in first out (FIFO) queue using only two stacks. The implemented queue should support all the functions of a normal queue (push, peek, pop, and empty).

Implement the MyQueue class:

void push(int x) Pushes element x to the back of the queue.
int pop() Removes the element from the front of the queue and returns it.
int peek() Returns the element at the front of the queue.
boolean empty() Returns true if the queue is empty, false otherwise.
Notes:

You must use only standard operations of a stack, which means only push to top, peek/pop from top, size, and is empty operations are valid.
Depending on your language, the stack may not be supported natively. You may simulate a stack using a list or deque (double-ended queue) as long as you use only a stack's standard operations.

https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1386/
*/

package queueAndStack;

import java.util.Stack;

public class ImplementQueueUsingStacks {
  private Stack<Integer> inStack;
  private Stack<Integer> outStack;

  public MyQueue() {
    inStack = new Stack<>();
    outStack = new Stack<>();
  }
  
  public void push(int x) {
    inStack.push(x);
  }
  
  public int pop() {
    shiftIfNeeded();
    return outStack.pop();
  }
  
  public int peek() {
    shiftIfNeeded();
    return outStack.peek();
  }
  
  public boolean empty() {
    return inStack.isEmpty() && outStack.isEmpty();
  }

  private void shiftIfNeeded() {
    if (outStack.isEmpty()) {
      while (!inStack.isEmpty()) {
        outStack.push(inStack.pop());
      }
    }
  }
}
