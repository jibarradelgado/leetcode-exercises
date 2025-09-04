/* You're given strings jewels representing the types of stones that are jewels, and stones representing the stones you have. Each character in stones is a type of stone you have. You want to know how many of the stones you have are also jewels.

Letters are case sensitive, so "a" is considered a different type of stone from "A".

https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1136/
*/

package hashTable;

import java.util.HashSet;
import java.util.Set;

public class JewelsAndStones {
    public int numJewelsInStones(String jewels, String stones) {
      Set<Character> jSet = new HashSet<>();
      for (char j: jewels.toCharArray()) {
        jSet.add(j);
      }

      int ans = 0;
      for (char s: stones.toCharArray()) {
        if (jSet.contains(s)) {
          ans++;
        }
      }
      return ans;
    }
}
