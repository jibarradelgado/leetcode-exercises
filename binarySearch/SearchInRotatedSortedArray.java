/* There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, nums is possibly left rotated at an unknown index k (1 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). For example, [0,1,2,4,5,6,7] might be left rotated by 3 indices and become [4,5,6,7,0,1,2].

Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.

You must write an algorithm with O(log n) runtime complexity.

https://leetcode.com/explore/learn/card/binary-search/125/template-i/952/
*/

package binarySearch;

public class SearchInRotatedSortedArray {
  public int search(int[] nums, int target) {
    int n = nums.length;
    int left = 0, right = n - 1;

    while (left <= right) {
      int mid = (left + right) / 2;
      if (nums[mid] > nums[n - 1]) {
        left = mid + 1;
      } else {
        right = mid -1;
      }
    }

    return shiftedBinarySearch(nums, left, target);
  }

  private int shiftedBinarySearch(int[] nums, int pivot, int target) {
    int n = nums.length;
    int shift = n - pivot;
    int left = (pivot + shift) % n;
    int right = (pivot - 1 + shift) % n;

    while (left <= right) {
      int mid = (left + right) / 2;
      if (nums[(mid - shift + n) % n] == target) {
        return (mid - shift + n) % n;
      } else if (nums[(mid - shift + n) % n] > target) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }
}
