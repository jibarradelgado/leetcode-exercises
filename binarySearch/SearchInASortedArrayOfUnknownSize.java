/* This is an interactive problem.

You have a sorted array of unique elements and an unknown size. You do not have an access to the array but you can use the ArrayReader interface to access it. You can call ArrayReader.get(i) that:

returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
returns 231 - 1 if the i is out of the boundary of the array.
You are also given an integer target.

Return the index k of the hidden array where secret[k] == target or return -1 otherwise.

You must write an algorithm with O(log n) runtime complexity.

https://leetcode.com/explore/learn/card/binary-search/136/template-analysis/1061/
*/

package binarySearch;

/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */
public class SearchInASortedArrayOfUnknownSize {
   public int search(ArrayReader reader, int target) {
    if (reader.get(0) == target) return 0;

    int left = 0, right = 1;
    while (reader.get(right) < target) {
      left = right;
      right <<= 1;
    } 

    int pivot, num;
    while (left <= right) {
      pivot = left + ((right - left) >> 1);
      num = reader.get(pivot);

      if (num == target) return pivot;
      if (num > target) right = pivot - 1;
      else left = pivot + 1;
    }

    return -1;
  }
}
