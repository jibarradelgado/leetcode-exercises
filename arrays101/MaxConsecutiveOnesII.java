/* Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0. */
// https://leetcode.com/explore/learn/card/fun-with-arrays/523/conclusion/3230/

package arrays101;

public class MaxConsecutiveOnesII {
    public int findMaxConsecutiveOnes(int[] nums) {
        int left = 0;
        int zeroCount = 0;
        int maxLen = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }

            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
