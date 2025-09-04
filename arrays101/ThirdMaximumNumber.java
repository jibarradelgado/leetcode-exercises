/* Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number. */
// https://leetcode.com/explore/learn/card/fun-with-arrays/523/conclusion/3231/

package arrays101;

public class ThirdMaximumNumber {
  public int thirdMax(int[] nums) {
    Long first = null, second = null, third = null;

    for (int num : nums) {
        long n = (long) num; // avoid overflow and null comparison

        if ((first != null && n == first) || 
            (second != null && n == second) || 
            (third != null && n == third)) {
            continue; // skip duplicates
        }

        if (first == null || n > first) {
            third = second;
            second = first;
            first = n;
        } else if (second == null || n > second) {
            third = second;
            second = n;
        } else if (third == null || n > third) {
            third = n;
        }
    }

    return third == null ? first.intValue() : third.intValue();
  }
}
