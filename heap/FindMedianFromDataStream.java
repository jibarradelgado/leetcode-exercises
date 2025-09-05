/* The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.

For example, for arr = [2,3,4], the median is 3.
For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
Implement the MedianFinder class:

MedianFinder() initializes the MedianFinder object.
void addNum(int num) adds the integer num from the data stream to the data structure.
double findMedian() returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.

https://leetcode.com/explore/learn/card/heap/646/practices/4092/
*/

package heap;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedianFromDataStream {
  private PriorityQueue<Integer> low;
  private PriorityQueue<Integer> high;

  public FindMedianFromDataStream() {
    low = new PriorityQueue<>(Collections.reverseOrder());
    high = new PriorityQueue<>();
  }
  
  public void addNum(int num) {
    if (low.isEmpty() || num <= low.peek()) {
      low.offer(num);
    } else {
      high.offer(num);
    }

    if (low.size() > high.size() + 1) {
      high.offer(low.poll());
    } else if (high.size() > low.size()) {
      low.offer(high.poll());
    }
  }
  
  public double findMedian() {
    if (low.size() == high.size()) {
      return (low.peek() + high.peek()) / 2.0;
    } else {
      return low.peek();
    }
  }
}
