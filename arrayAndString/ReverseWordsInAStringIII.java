/* Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 * 
 * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1165/
 */

package arrayAndString;

public class ReverseWordsInAStringIII {
    public String reverseWords(String s) {
      s = s.trim();
      String[] words = s.split("\\s+");
      StringBuilder result = new StringBuilder();

      for (int i = 0; i < words.length; i++) {
        result.append(new StringBuilder(words[i]).reverse());
        if (i < words.length - 1) {
          result.append(" ");
        }
      }

      return result.toString();
    }
}
