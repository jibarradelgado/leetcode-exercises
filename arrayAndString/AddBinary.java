/* Given two binary strings a and b, return their sum as a binary string.
 * 
 * https://leetcode.com/explore/learn/card/array-and-string/203/introduction-to-string/1160/
 */

package arrayAndString;

public class AddBinary {
    public String addBinary(String a, String b) {
      int n = a.length(), m = b.length();
      if (n < m) return addBinary(b, a);

      StringBuilder sb = new StringBuilder();
      int carry = 0, j = m - 1;
      for (int i = n - 1; i > -1; --i) {
        if(a.charAt(i) == '1') ++carry;
        if(j > -1 && b.charAt(j--) == '1') ++carry;

        if (carry % 2 == 1) sb.append('1');
        else sb.append('0');

        carry /= 2;
      }
      if (carry == 1) sb.append('1');
      sb.reverse();

      return sb.toString();
    }
}
