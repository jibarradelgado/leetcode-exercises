//Given an array nums of integers, return how many of them contain an even number of digits.
//https://leetcode.com/explore/learn/card/fun-with-arrays/521/introduction/3237/

package arrays101;

public class FindNumbersWithEvenNumberDigits {
  public int findNumbers(int[] nums) {
        int result = 0;
        int digitSum;
        
        for(int num : nums) {
            digitSum = 1;
            while(num / 10 > 0) {
                digitSum++;
                num = num/10; 
            }
            if(digitSum != 0 && digitSum % 2 == 0) {
                result++;
            }
        }
        
        return result; 
    }
}
