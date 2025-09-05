/* Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:

MovingAverage(int size) Initializes the object with the size of the window size.
double next(int val) Returns the moving average of the last size values of the stream.

https://leetcode.com/explore/learn/card/queue-stack/228/first-in-first-out-data-structure/1368/
*/

package queueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MovingAverage {
  int size, windowSum = 0, count = 0;
  Deque<Integer> queue = new ArrayDeque<Integer>();

  public MovingAverage(int size) {
    this.size = size;
  }
  
  public double next(int val) {
    ++count;

    queue.add(val);
    int tail = count > size ? (int) queue.poll() : 0;
    windowSum = windowSum - tail + val;
    return (windowSum * 1.0) / Math.min(size, count); 
  }
}
