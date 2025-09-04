/* Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the array. */
// https://leetcode.com/explore/learn/card/fun-with-arrays/511/in-place-operations/3157/

package arrays101;

public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        int lastNonZero = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                nums[lastNonZero] = nums[i];
                lastNonZero++;
            }
        }
        
        for (int i = lastNonZero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
