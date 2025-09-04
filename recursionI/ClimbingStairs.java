/* You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?


*/

package recursionI;

public class ClimbingStairs {
  public int climbStairs(int n) {
    int memo[] = new int[n + 1];
    return climbStairs(0, n, memo);
  }

  public int climbStairs(int i, int n, int memo[]) {
    if (i > n) {
      return 0;
    }
    if (i == n) {
      return 1;
    }
    if (memo[i] > 0) {
      return memo[i];
    }
    memo[i] = climbStairs(i + 1, n, memo) + climbStairs(i + 2, n, memo);
    return memo[i];
  }
}
