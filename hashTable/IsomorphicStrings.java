/* Given two strings s and t, determine if they are isomorphic.

Two strings s and t are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character, but a character may map to itself.

https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1117/
*/

package hashTable;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
  private String transformString(String s) {
    Map<Character, Integer> indexMapping = new HashMap<>();
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < s.length(); i++) {
      char c1 = s.charAt(i);

      if (!indexMapping.containsKey(c1)) {
        indexMapping.put(c1, i);
      }

      builder.append(Integer.toString(indexMapping.get(c1)));
      builder.append(" ");
    }
    return builder.toString();
  }

  public boolean isIsomorphic(String s, String t) {
    return transformString(s).equals(transformString(t));
  }
}
