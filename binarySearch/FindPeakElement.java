/* A peak element is an element that is strictly greater than its neighbors.

Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.

You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.

You must write an algorithm that runs in O(log n) time.

https://leetcode.com/explore/learn/card/binary-search/126/template-ii/948/
*/

package binarySearch;

public class FindPeakElement {
  public int findPeakElement(int[] nums) {
    return search(nums, 0, nums.length - 1);
  }

  public int search(int[] nums, int l, int r) {
    if (l == r) return l;
    int mid = (l + r) / 2;
    if (nums[mid] > nums[mid + 1]) return search(nums, l, mid);
    return search(nums, mid + 1, r);
  }
}
