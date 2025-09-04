/* Given a string s, find the first non-repeating character in it and return its index. If it does not exist, return -1.
 * 
 * 
 * https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1120/
 */

package hashTable;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterInAString {
  public int firstUniqChar(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
      if(map.containsKey(c)) {
        map.put(c, map.get(c) + 1);
      }
      else {
        map.put(c, 1);
      }
    }
    
    for (int i = 0; i < s.length(); i++) {
      if (map.get(s.charAt(i)) == 1) return i; 
    }
    return -1;
  }
}
