/* Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

Implement the MinStack class:

MinStack() initializes the stack object.
void push(int val) pushes the element val onto the stack.
void pop() removes the element on the top of the stack.
int top() gets the top element of the stack.
int getMin() retrieves the minimum element in the stack.
You must implement a solution with O(1) time complexity for each function.

https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1360/
*/

package queueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
  private final Deque<Integer> stack;
  private final Deque<Integer> mins;

    public MinStack() {
      stack = new ArrayDeque<>();
      mins = new ArrayDeque<>();
    }
    
    public void push(int val) {
      stack.push(val);
      if (mins.isEmpty()) mins.push(val);
      else mins.push(Math.min(mins.peek(), val));
    }
    
    public void pop() {
      stack.pop();
      mins.pop();
    }
    
    public int top() {
      return stack.peek();
    }
    
    public int getMin() {
      return mins.peek();
    }
}
