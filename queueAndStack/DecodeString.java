/* Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.


https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1379/
*/

package queueAndStack;

import java.util.Stack;

public class DecodeString {
  public String decodeString(String s) {
    Stack<Integer> countStack = new Stack<>();
    Stack<StringBuilder> stringStack = new Stack<>();
    StringBuilder currentString = new StringBuilder();
    int k = 0;
    for (char ch : s.toCharArray()) {
      if (Character.isDigit(ch)) {
        k = k * 10 + ch - '0';
      } else if (ch == '[') {
        countStack.push(k);
        stringStack.push(currentString);
        currentString = new StringBuilder();
        k = 0;
      } else if (ch == ']') {
        StringBuilder decodedString = stringStack.pop();
        for (int currentK = countStack.pop(); currentK > 0; currentK--) {
          decodedString.append(currentString);
        }
        currentString = decodedString;
      } else {
        currentString.append(ch);
      }
    }
    return currentString.toString();
  }
}
