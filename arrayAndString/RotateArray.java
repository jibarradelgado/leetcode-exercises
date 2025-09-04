/* Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
 * 
 * https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1182/
 */

package arrayAndString;

public class RotateArray {
    public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
