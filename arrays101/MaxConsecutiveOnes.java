//Given a binary array nums, return the maximum number of consecutive 1's in the array.
//https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3238/

package arrays101;

class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int current = 0;
        
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 1) {
                current++;    
            } 
            if (nums[i] == 0 || i == nums.length - 1) {
                if (max == 0 || current > max) {
                    max = current;
                }
                current = 0; 
            }
        }
        
        return max;
    }
}

