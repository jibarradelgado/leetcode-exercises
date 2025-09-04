/* Implement the RandomizedSet class:

RandomizedSet() Initializes the RandomizedSet object.
bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
You must implement the functions of the class such that each function works in average O(1) time complexity.

https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1141/
*/

package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class InsertDeleteGetRandom {
    private final List<Integer> arr;           // dense storage
    private final Map<Integer, Integer> index; // val -> idx in arr
    private final Random rng;

    public RandomizedSet() {
        this.arr = new ArrayList<>();
        this.index = new HashMap<>();
        this.rng = new Random();
    }
    
    public boolean insert(int val) {
        if (index.containsKey(val)) return false;  // already present
        index.put(val, arr.size());
        arr.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        Integer i = index.get(val);
        if (i == null) return false;               // not present
        
        int lastIdx = arr.size() - 1;
        int lastVal = arr.get(lastIdx);
        
        // Move the last element into position i (if i != lastIdx)
        arr.set(i, lastVal);
        index.put(lastVal, i);
        
        // Remove the last slot
        arr.remove(lastIdx);
        index.remove(val);
        return true;
    }
    
    public int getRandom() {
        int i = rng.nextInt(arr.size());           // uniform 0..size-1
        return arr.get(i);
    }
}
