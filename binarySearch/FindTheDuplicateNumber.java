/* Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.

There is only one repeated number in nums, return this repeated number.

You must solve the problem without modifying the array nums and using only constant extra space.

https://leetcode.com/explore/learn/card/binary-search/146/more-practices-ii/1039/
*/

package binarySearch;

public class FindTheDuplicateNumber {
  public int findDuplicate(int[] nums) {
    int low = 1, high = nums.length - 1;
    int duplicate = -1;

    while (low <= high) {
      int cur = (low + high) / 2;

      int count = 0;
      for (int num : nums) {
        if (num <= cur) {
          count++;
        }
      }

      if (count > cur) {
        duplicate = cur;
        high = cur - 1;
      } else {
        low = cur + 1;
      }
    }
    return duplicate;
  }
}
