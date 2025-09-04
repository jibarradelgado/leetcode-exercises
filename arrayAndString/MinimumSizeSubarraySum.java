/* Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * 
 * 
 * https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1299/
 */

package arrayAndString;

public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
      int left = 0, right = 0, sumOfCurrentWindow = 0;
      int res = Integer.MAX_VALUE;

      for (right = 0; right < nums.length; right++) {
        sumOfCurrentWindow += nums[right];

        while(sumOfCurrentWindow >= target) {
          res = Math.min(res, right - left  + 1);
          sumOfCurrentWindow -= nums[left++];
        }
      }

      return res == Integer.MAX_VALUE ? 0 : res;
    }
}
