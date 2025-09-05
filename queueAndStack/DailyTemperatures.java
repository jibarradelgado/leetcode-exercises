/* Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.
 * 
 * 
 * https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1363/
 */

package queueAndStack;

import java.util.ArrayDeque;
import java.util.Deque;

public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
      int n = temperatures.length;
      int[] answer = new int[n];
      Deque<Integer> stack = new ArrayDeque<>();

      for (int currDay = 0; currDay < n; currDay++) {
        int currentTemp = temperatures[currDay];

        while (!stack.isEmpty() && temperatures[stack.peek()] < currentTemp) {
          int prevDay = stack.pop();
          answer[prevDay] = currDay - prevDay;
        }
        stack.push(currDay);
      }
      return answer;
    }
}
