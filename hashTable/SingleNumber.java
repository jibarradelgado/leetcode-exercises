/* Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

You must implement a solution with a linear runtime complexity and use only constant extra space.

https://leetcode.com/explore/learn/card/hash-table/183/combination-with-other-algorithms/1176/
*/

package hashTable;

import java.util.HashSet;

public class SingleNumber {
    public int singleNumber(int[] nums) {
      HashSet<Integer> set = new HashSet<Integer>();
      
      for(int num : nums) {
        if (set.contains(num)) {
          set.remove(num);
        } else {
          set.add(num);  
        }
      }
      
      return set.iterator().next();
    }
}
