/* Given a positive integer num, return true if num is a perfect square or false otherwise.

A perfect square is an integer that is the square of an integer. In other words, it is the product of some integer with itself.

You must not use any built-in library function, such as sqrt.

https://leetcode.com/explore/learn/card/binary-search/137/conclusion/978/
*/

package binarySearch;

public class ValidPerfectSquare {
  public boolean isPerfectSquare(int num) {
    if (num < 2) {
      return true;
    }    

    long left = 2, right = num / 2, x, guessSquared;
    while (left <= right) {
      x = left + (right - left) / 2;
      guessSquared = x * x;
      if (guessSquared == num) {
        return true;
      }
      if (guessSquared > num) {
        right = x - 1;
      } else {
        left = x + 1;
      }
    }return false;
  }
}
