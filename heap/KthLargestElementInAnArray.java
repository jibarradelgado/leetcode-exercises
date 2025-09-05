/* Given an integer array nums and an integer k, return the kth largest element in the array.

Note that it is the kth largest element in the sorted order, not the kth distinct element.

Can you solve it without sorting?

https://leetcode.com/explore/learn/card/heap/646/practices/4014/
*/

package heap;

import java.util.PriorityQueue;

public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
      PriorityQueue<Integer> heap = new PriorityQueue<>();
      for (int num : nums) {
        heap.add(num);
        if (heap.size() > k) {
          heap.remove();
        }
      }

      return heap.peek();
    }
}
