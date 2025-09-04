/* 
 * Write a function that reverses a string. The input string is given as an array of characters s.

You must do this by modifying the input array in-place with O(1) extra memory.

https://leetcode.com/explore/learn/card/array-and-string/205/array-two-pointer-technique/1183/
 */

package arrayAndString;

public class ReverseString {
    public void reverseString(char[] s) {
      int p1 = 0;
      int p2 = s.length - 1;
      char temp; 
      
      while(p1 < p2) {
        temp = s[p1];
        s[p1] = s[p2];
        s[p2] = temp;
        
        p1++;
        p2--;
      }
    }
}
