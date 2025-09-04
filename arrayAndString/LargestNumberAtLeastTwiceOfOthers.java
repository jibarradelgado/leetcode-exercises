/* ou are given an integer array nums where the largest integer is unique.

Determine whether the largest element in the array is at least twice as much as every other number in the array. If it is, return the index of the largest element, or return -1 otherwise.

https://leetcode.com/explore/learn/card/array-and-string/201/introduction-to-array/1147/
*/

package arrayAndString;

public class LargestNumberAtLeastTwiceOfOthers {
    public int dominantIndex(int[] nums) {
      int maxIndex = 0;
      
      for(int i = 0; i < nums.length; i++) {
        if (nums[i] > nums[maxIndex]) {
          maxIndex = i;
        }
      }
      
      for(int i = 0; i < nums.length; i++) {
        if(i != maxIndex && nums[i] * 2 > nums[maxIndex]) {
          return -1;
        }
      }
      
      return maxIndex;
    }
}
