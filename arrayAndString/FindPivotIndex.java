/* Given an array of integers nums, calculate the pivot index of this array.

The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.

https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1144/
*/

package arrayAndString;

public class FindPivotIndex {
    public int pivotIndex(int[] nums) {
      int sum = 0, leftsum = 0;
      for (int x: nums) sum += x;
      for (int i = 0; i < nums.length; i++) {
        if (leftsum == sum - leftsum - nums[i]) {
          return i;
        }

        leftsum += nums[i];
      }
      return -1;
    }
}
