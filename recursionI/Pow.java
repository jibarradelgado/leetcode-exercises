/* Implement pow(x, n), which calculates x raised to the power n (i.e., xn).
 * 
 * 
 * https://leetcode.com/explore/learn/card/recursion-i/256/complexity-analysis/2380/
 */

package recursionI;

public class Pow {
  private double binaryExp(double x, long n) {
    if (n == 0) {
      return 1;
    }

    if (n < 0) {
      return 1.0 / binaryExp(x, -1 * n);
    }

    if (n % 2 == 1) {
      return x * binaryExp(x * x, (n - 1) / 2);
    }
    else {
      return binaryExp(x * x, n / 2);
    }
  }

  public double myPow(double x, int n) {
    return binaryExp(x, (long) n);
  }
}
