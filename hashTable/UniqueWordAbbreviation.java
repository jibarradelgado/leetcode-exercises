/* The abbreviation of a word is a concatenation of its first letter, the number of characters between the first and last letter, and its last letter. If a word has only two characters, then it is an abbreviation of itself.

For example:

dog --> d1g because there is one letter between the first letter 'd' and the last letter 'g'.
internationalization --> i18n because there are 18 letters between the first letter 'i' and the last letter 'n'.
it --> it because any word with only two characters is an abbreviation of itself.
Implement the ValidWordAbbr class:

ValidWordAbbr(String[] dictionary) Initializes the object with a dictionary of words.
boolean isUnique(string word) Returns true if either of the following conditions are met (otherwise returns false):
There is no word in dictionary whose abbreviation is equal to word's abbreviation.
For any word in dictionary whose abbreviation is equal to word's abbreviation, that word and word are the same.

https://leetcode.com/explore/learn/card/hash-table/187/conclusion-hash-table/1137/
*/

package hashTable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class UniqueWordAbbreviation {
  private final Map<String, String> abbrMap = new HashMap<>();
  private static final String MULTI = "#";

  public void ValidWordAbbr(String[] dictionary) {
    Set<String> uniq = new HashSet<>(Arrays.asList(dictionary));
    for (String w : uniq) {
      String a = abbr(w);
      String prev = abbrMap.get(a);
      if (prev == null) {
        abbrMap.put(a, w);
      } else if (!prev.equals(w)) {
        abbrMap.put(a, MULTI);
      }
    }
  }
  
  public boolean isUnique(String word) {
    String a = abbr(word);
    String mapped = abbrMap.get(a);
    return mapped == null || mapped.equals(word);
  }

  private String abbr(String w) {
    int n = w.length();
    if (n <= 2) return w;
    return "" + w.charAt(0) + (n - 2) + w.charAt(n - 1);
  }
}