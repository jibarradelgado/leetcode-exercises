/* Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
 * 
 * https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1178/
 */

package hashTable;

import java.util.Arrays;
import java.util.HashMap;

public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
      if (nums1.length > nums2.length) {
        return intersect(nums2, nums1);
      }
      HashMap<Integer, Integer> m = new HashMap<>();
      for (int n : nums1) {
        m.put(n, m.getOrDefault(n, 0) + 1);
      }
      int k = 0;
      for (int n : nums2) {
        int cnt = m.getOrDefault(n, 0);
        if (cnt > 0) {
          nums1[k++] = n;
          m.put(n, cnt - 1);
        }
      }
      return Arrays.copyOfRange(nums1, 0, k);
    }
}
