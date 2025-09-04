/* Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:

0 <= i, j, k, l < n
nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1134/
*/

package hashTable;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
      int cnt = 0;
      Map<Integer, Integer> m = new HashMap<>();
      for (int a : nums1) {
          for (int b : nums2) {
              m.put(a + b, m.getOrDefault(a + b, 0) + 1);
          }
      }
      for (int c : nums3) {
          for (int d : nums4) {
              cnt += m.getOrDefault(-(c + d), 0);
          }
      }
      return cnt;
    }
}
