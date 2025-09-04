/* Perform the following shift operations on a string:

Right shift: Replace every letter with the successive letter of the English alphabet, where 'z' is replaced by 'a'. For example, "abc" can be right-shifted to "bcd" or "xyz" can be right-shifted to "yza".
Left shift: Replace every letter with the preceding letter of the English alphabet, where 'a' is replaced by 'z'. For example, "bcd" can be left-shifted to "abc" or "yza" can be left-shifted to "xyz".
We can keep shifting the string in both directions to form an endless shifting sequence.

For example, shift "abc" to form the sequence: ... <-> "abc" <-> "bcd" <-> ... <-> "xyz" <-> "yza" <-> .... <-> "zab" <-> "abc" <-> ...
You are given an array of strings strings, group together all strings[i] that belong to the same shifting sequence. You may return the answer in any order.

https://leetcode.com/explore/learn/card/hash-table/185/hash_table_design_the_key/1125/
*/

package hashTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupShiftedStrings {
    String getHash(String s) {
        char[] chars = s.toCharArray();
        StringBuilder hashKey = new StringBuilder();
        
        for (int i = 1; i < chars.length; i++) {
            hashKey.append((char) ((chars[i] - chars[i - 1] + 26) % 26 + 'a'));
        }
        
        return hashKey.toString();
    }
    
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> mapHashToList = new HashMap<>();
        
        // Create a hash_value (hashKey) for each string and append the string
        // to the list of hash values i.e. mapHashToList["cd"] = ["acf", "gil", "xzc"]
        for (String str : strings ) {
            String hashKey = getHash(str);
            if (mapHashToList.get(hashKey) == null) {
                mapHashToList.put(hashKey, new ArrayList<>());
            } 
            mapHashToList.get(hashKey).add(str);
        }
        
        // Iterate over the map, and add the values to groups
        List<List<String>> groups = new ArrayList<>();
        for (List<String> group : mapHashToList.values()) {
            groups.add(group);
        }
        
        // Return a list of all of the grouped strings
        return groups;
    }
}
