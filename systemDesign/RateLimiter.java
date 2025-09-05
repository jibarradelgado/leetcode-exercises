/* A Rate Limiting System can allow a maximum of n requests every t seconds, using an implementation similar to the sliding window algorithm.

Given two positive integers n and t, and a non-decreasing stream of integers representing the timestamps of requests, implement a data structure that can check if each request is allowed or not.

Implement the RateLimiter class:

RateLimiter(int n, int t) Initializes the RateLimiter object with an empty stream and two integers n and t.
boolean shouldAllow(int timestamp) Returns true if the current request with timestamp timestamp is allowed, otherwise returns false. Note that while checking if the current request should be allowed, only the timestamps of requests previously allowed are considered.

https://leetcode.com/explore/learn/card/system-design/689/system-design-basics/4408/
*/

package systemDesign;

import java.util.ArrayDeque;
import java.util.Deque;

public class RateLimiter {
  private final int n; 
  private final int t; 
  private final Deque<Integer> allowed;

    public RateLimiter(int n, int t) {
      this.n = n;
      this.t = t;
      this.allowed = new ArrayDeque<>();
    }
    
    public boolean shouldAllow(int timestamp) {
      int oldestAllowed = timestamp - t + 1;
      while (!allowed.isEmpty() && allowed.peekFirst() < oldestAllowed) {
        allowed.pollFirst();
      }
      
      if (allowed.size() < n) {
        allowed.offerLast(timestamp);
        return true;
      } else {
        return false;
      }
    }
}
