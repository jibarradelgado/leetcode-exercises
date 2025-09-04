/* Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array such that nums[i] == nums[j] and abs(i - j) <= k.
 * 
 * https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1121/
 */

package hashTable;

import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicateII {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
      Set<Integer> set = new HashSet<>();
      for (int i = 0; i < nums.length; ++i) {
        if (set.contains(nums[i])) return true;
        set.add(nums[i]);
        if (set.size() > k) {
          set.remove(nums[i - k]);
        }
      }
      return false;
    }
}
