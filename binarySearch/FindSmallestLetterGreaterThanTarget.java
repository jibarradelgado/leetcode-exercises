/* You are given an array of characters letters that is sorted in non-decreasing order, and a character target. There are at least two different characters in letters.

Return the smallest character in letters that is lexicographically greater than target. If such a character does not exist, return the first character in letters.

https://leetcode.com/explore/learn/card/binary-search/137/conclusion/977/
*/

package binarySearch;

public class FindSmallestLetterGreaterThanTarget {
  public char nextGreatestLetter(char[] letters, char target) {
    int left = 0, right = letters.length - 1, mid;
    while (left <= right) {
      mid = (left + right) / 2;
      if (letters[mid] <= target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return left == letters.length ? letters[0] : letters[left];
  }
}
