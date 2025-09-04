/* Given an array of strings strs, group the anagrams together. You can return the answer in any order.
 * 
 * https://leetcode.com/explore/learn/card/hash-table/185/hash_table_design_the_key/1124/
 */

package hashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
      if (strs.length == 0) return new ArrayList();
      Map<String, List> ans = new HashMap<String, List>();
      for (String s : strs) {
          char[] ca = s.toCharArray();
          Arrays.sort(ca);
          String key = String.valueOf(ca);
          if (!ans.containsKey(key)) ans.put(key, new ArrayList());
          ans.get(key).add(s);
      }
      return new ArrayList(ans.values());
    }
}
