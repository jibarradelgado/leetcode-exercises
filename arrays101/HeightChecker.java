/* A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in non-decreasing order by height. Let this ordering be represented by the integer array expected where expected[i] is the expected height of the ith student in line.

You are given an integer array heights representing the current order that the students are standing in. Each heights[i] is the height of the ith student in line (0-indexed).

Return the number of indices where heights[i] != expected[i]. */
// https://leetcode.com/explore/learn/card/fun-with-arrays/523/conclusion/3228/

package arrays101;

import java.util.Arrays;

public class HeightChecker {
    public int heightChecker(int[] heights) {
        int[] expected = Arrays.copyOf(heights, heights.length);
        Arrays.sort(expected);
        int result = 0;
        for(int i = 0; i < heights.length; i++) {
            if(heights[i] != expected[i]) {
                result++;
            }
        }
        
        return result;
    }
}
