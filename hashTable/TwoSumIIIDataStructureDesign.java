/* Design a data structure that accepts a stream of integers and checks if it has a pair of integers that sum up to a particular value.

Implement the TwoSum class:

TwoSum() Initializes the TwoSum object, with an empty array initially.
void add(int number) Adds number to the data structure.
boolean find(int value) Returns true if there exists any pair of numbers whose sum is equal to value, otherwise, it returns false.

https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1179/
*/

package hashTable;

import java.util.HashMap;
import java.util.Map;

public class TwoSumIIIDataStructureDesign {
  private HashMap<Integer, Integer> num_counts;

  public TwoSum() {
    this.num_counts = new HashMap<Integer, Integer>();
  }
  
  public void add(int number) {
    if (this.num_counts.containsKey(number)) {
      this.num_counts.replace(number, this.num_counts.get(number) + 1);
    }
    else {
      this.num_counts.put(number, 1);
    }
  }
  
  public boolean find(int value) {
    for (Map.Entry<Integer, Integer> entry: this.num_counts.entrySet()) {
      int complement = value - entry.getKey();
      if (complement != entry.getKey()) {
        if (this.num_counts.containsKey(complement)) return true;
      } else {
        if (entry.getValue() > 1) return true;
      }
    }
    return false;
  }
}
