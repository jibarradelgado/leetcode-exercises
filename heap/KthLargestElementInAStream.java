/* You are part of a university admissions office and need to keep track of the kth highest test score from applicants in real-time. This helps to determine cut-off marks for interviews and admissions dynamically as new applicants submit their scores.

You are tasked to implement a class which, for a given integer k, maintains a stream of test scores and continuously returns the kth highest test score after a new score has been submitted. More specifically, we are looking for the kth highest score in the sorted list of all scores.

Implement the KthLargest class:

KthLargest(int k, int[] nums) Initializes the object with the integer k and the stream of test scores nums.
int add(int val) Adds a new test score val to the stream and returns the element representing the kth largest element in the pool of test scores so far.

https://leetcode.com/explore/learn/card/heap/646/practices/4016/
*/

package heap;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {
  PriorityQueue<Integer> minHeap;
  int k;

  public KthLargestElementInAStream(int k, int[] nums) {
    minHeap = new PriorityQueue<>();
    this.k = k;

    for (int num : nums) {
      add(num);
    }  
  }
  
  public int add(int val) {
    if (minHeap.size() < k || minHeap.peek() < val) {
      minHeap.add(val);
      if (minHeap.size() > k) {
        minHeap.remove();
      }
    }
    return minHeap.peek();
  }
}
