/* Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 * 
 * https://leetcode.com/explore/learn/card/hash-table/183/combination-with-other-algorithms/1112/
 */

package hashTable;

import java.util.HashSet;

public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
      HashSet<Integer> set = new HashSet<Integer>();
      
      for (int num : nums) {
        if (set.contains(num)) {
          return true;
        }
        set.add(num);
      }
      return false;
    }
}
