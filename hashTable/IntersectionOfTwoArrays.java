/* Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must be unique and you may return the result in any order.
 * 
 * 
 * https://leetcode.com/explore/learn/card/hash-table/183/combination-with-other-algorithms/1105/
 */

package hashTable;

import java.util.HashSet;

public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
      HashSet<Integer> set = new HashSet<Integer>();
      HashSet<Integer> result = new HashSet<Integer>();
      
      for (int num : nums1) {
        set.add(num);
      }
      
      for (int num: nums2) {
        if (set.contains(num)) {
          result.add(num);
        }
      }
      
      return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
