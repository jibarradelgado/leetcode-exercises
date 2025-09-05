/* Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

https://leetcode.com/explore/learn/card/binary-search/146/more-practices-ii/1040/
*/

package binarySearch;

public class MedianOfTwoSortedArrays {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int na = nums1.length, nb = nums2.length;
    int n = na + nb;
    if ((na + nb) % 2 == 1) {
      return solve(nums1, nums2, n / 2, 0, na - 1, 0, nb - 1);
    } else {
      return (double) (solve(nums1, nums2, n / 2, 0, na - 1, 0, nb - 1) +
                        solve(nums1, nums2, n / 2 - 1, 0, na - 1, 0, nb - 1)) / 2;
    }
  }

  public int solve(
    int[] A,
    int[] B,
    int k,
    int aStart,
    int aEnd,
    int bStart,
    int bEnd
  ) {
    if (aEnd < aStart) {
      return B[k - aStart];
    }
    if (bEnd < bStart) {
      return A[k - bStart];
    }

    int aIndex = (aStart + aEnd) / 2, bIndex = (bStart + bEnd) / 2;
    int aValue = A[aIndex], bValue = B[bIndex];

    if (aIndex + bIndex < k) {
      if (aValue > bValue) {
        return solve(A, B, k, aStart, aEnd, bIndex + 1, bEnd);
      } else {
        return solve(A, B, k, aIndex + 1, aEnd, bStart, bEnd);
      }
    }
    else {
      if (aValue > bValue) {
        return solve(A, B, k, aStart, aIndex - 1, bStart, bEnd);
      } else {
        return solve(A, B, k, aStart, aEnd, bStart, bIndex - 1);
      }
    }
  }
}
