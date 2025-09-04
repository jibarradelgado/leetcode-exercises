/* Given a string s, find the length of the longest substring without duplicate characters.
 * 
 * https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1135/
 */

package hashTable;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
      int n = s.length();
      int ans = 0;
      Map<Character, Integer> map = new HashMap<>();

      for (int right = 0, left = 0; right < n; right++) {
          // do logic here to add arr[right] to curr
          if(map.containsKey(s.charAt(right))) {
              left = Math.max(map.get(s.charAt(right)), left);
          }

          ans = Math.max(ans, right - left + 1);
          map.put(s.charAt(right), right + 1);
      }
      return ans;
    }
}
