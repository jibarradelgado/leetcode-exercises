/* The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,

F(0) = 0, F(1) = 1
F(n) = F(n - 1) + F(n - 2), for n > 1.
Given n, calculate F(n).


*/

package recursionI;

import java.util.HashMap;
import java.util.Map;

public class FibonacciNumber {
  private Map<Integer, Integer> cache = new HashMap<Integer, Integer>() {{
    put(0, 0);
    put(1, 1);
  }};

  public int fib(int n) {
    if (cache.containsKey(n)) {
      return cache.get(n);
    }
    cache.put(n, fib(n - 1) + fib(n - 2));
    return cache.get(n);
  }
}
