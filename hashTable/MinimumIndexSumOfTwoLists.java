/* Given two arrays of strings list1 and list2, find the common strings with the least index sum.

A common string is a string that appeared in both list1 and list2.

A common string with the least index sum is a common string such that if it appeared at list1[i] and list2[j] then i + j should be the minimum value among all the other common strings.

Return all the common strings with the least index sum. Return the answer in any order.

https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1177/
*/

package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MinimumIndexSumOfTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
      HashMap<Integer, List<String>> map = new HashMap<>();
      for (int i = 0; i < list1.length; i++) {
        for (int j = 0; j < list2.length; j++) {
          if (list1[i].equals(list2[j])) {
            if (!map.containsKey(i + j)) {
              map.put(i + j, new ArrayList<String>());
            }
            map.get(i + j).add(list1[i]);
          }
        }
      }
      int minIndexSum = Integer.MAX_VALUE;
      for (int key: map.keySet()) {
        minIndexSum = Math.min(minIndexSum, key);
      }
      String[] res = new String[map.get(minIndexSum).size()];
      return map.get(minIndexSum).toArray(res); 
    }
}
