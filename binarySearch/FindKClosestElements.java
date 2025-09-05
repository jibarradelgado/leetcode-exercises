/* Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.

An integer a is closer to x than an integer b if:

|a - x| < |b - x|, or
|a - x| == |b - x| and a < b

https://leetcode.com/explore/learn/card/binary-search/135/template-iii/945/
*/

package binarySearch;

import java.util.ArrayList;
import java.util.List;

public class FindKClosestElements {
  public List<Integer> findClosestElements(int[] arr, int k, int x) {
    List<Integer> result = new ArrayList<Integer>();

    if (arr.length == k) {
      for (int i = 0; i < k; i++) {
        result.add(arr[i]);
      }

      return result;
    }

    int left = 0;
    int right = arr.length;
    int mid = 0;
    while (left < right) {
      mid = (left + right) / 2;
      if (arr[mid] >= x) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }

    left -= 1;
    right = left + 1;

    while (right - left - 1 < k) {
      if (left == -1) {
        right += 1;
        continue;
      }

      if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] -x)) {
        left -= 1;
      } else {
        right += 1;
      }
    }

    for (int i = left + 1; i < right; i++) {
      result.add(arr[i]);
    }

    return result;
  }
}
