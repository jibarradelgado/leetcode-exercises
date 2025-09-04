/* We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.

For example, for n = 3, the 1st row is 0, the 2nd row is 01, and the 3rd row is 0110.
Given two integer n and k, return the kth (1-indexed) symbol in the nth row of a table of n rows.

https://leetcode.com/explore/learn/card/recursion-i/253/conclusion/1675/
*/

package recursionI;

public class KthSymbolInGrammar {
  public int depthFirstSearch(int n, int k, int rootVal) {
    if (n == 1) {
      return rootVal;
    }

    int totalNodes = (int) Math.pow(2, n - 1);

    if (k > (totalNodes / 2)) {
      int nextRootVal = (rootVal == 0) ? 1 : 0;
      return depthFirstSearch(n - 1, k - (totalNodes / 2), nextRootVal);
    }

    else {
      int nextRootVal = (rootVal == 0) ? 0 : 1;
      return depthFirstSearch(n - 1, k, nextRootVal);
    }
  }

  public int kthGrammar(int n, int k) {
    return depthFirstSearch(n, k, 0);
  }
}
