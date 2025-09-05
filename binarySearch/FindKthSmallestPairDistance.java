/* The distance of a pair of integers a and b is defined as the absolute difference between a and b.

Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.

https://leetcode.com/explore/learn/card/binary-search/146/more-practices-ii/1041/
*/


package binarySearch;

import java.util.Arrays;

public class FindKthSmallestPairDistance {
  public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums);
    int arraySize = nums.length;

    int low = 0;
    int high = nums[arraySize - 1] - nums[0];

    while (low < high) {
      int mid = (low + high) / 2;

      int count = countPairsWithMaxDistance(nums, mid);

      if (count < k) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }

    return low;
  }

  private int countPairsWithMaxDistance(int[] nums, int maxDistance) {
    int count = 0;
    int arraySize = nums.length;
    int left = 0;

    for (int right = 0; right < arraySize; ++right) {
      while (nums[right] - nums[left] > maxDistance) {
        ++left;
      }
      count += right - left;
    }
    return count;
  }
}
