/* Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums. */
// https://leetcode.com/explore/learn/card/fun-with-arrays/523/conclusion/3270/

package arrays101;

import java.util.ArrayList;
import java.util.List;

public class FindAllNumbersDisappearedInAnArray {
    public List<Integer> findDisappearedNumbers(int[] nums) {
      List<Integer> result = new ArrayList<>();

      // Step 1: Mark presence of each number by negating the value at its index
      for (int i = 0; i < nums.length; i++) {
          int index = Math.abs(nums[i]) - 1; // convert value to index
          if (nums[index] > 0) {
              nums[index] = -nums[index]; // mark as seen
          }
      }

      // Step 2: Find indexes that are still positive → missing numbers
      for (int i = 0; i < nums.length; i++) {
          if (nums[i] > 0) {
              result.add(i + 1); // because value = index + 1
          }
      }

      return result;
    }
}
