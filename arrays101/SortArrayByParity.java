/* Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.

Return any array that satisfies this condition. */
// https://leetcode.com/explore/learn/card/fun-with-arrays/511/in-place-operations/3260/

package arrays101;

public class SortArrayByParity {
    public int[] sortArrayByParity(int[] nums) {
        int odd = 0;
        int found = 0;
        
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] % 2 != 0) {
                odd = nums[i]; 
                for(int j=i; j < nums.length - 1; j++) {
                    nums[j] = nums[j + 1];    
                }
                nums[nums.length - 1] = odd;
                found++;
                if(i < nums.length - found) {
                    i--;
                }
            }
        }
        
        return nums;
    }
}
