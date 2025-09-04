/* Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 * 
 * 
 * https://leetcode.com/explore/learn/card/recursion-i/253/conclusion/2384/
 */


package recursionI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class UniqueBinarySearchTreesII {
  public List<TreeNode> allPossibleBST(
    int start,
    int end,
    Map<Pair<Integer,Integer>, List<TreeNode>> memo
  ) {
    List<TreeNode> res = new ArrayList<>();
    if (start > end) {
      res.add(null);
      return res;
    }
    if (memo.containsKey(new Pair<>(start,end))) {
      return memo.get(new Pair<>(start, end));
    }

    for (int i = start; i <= end; ++i) {
      List<TreeNode> leftSubTrees = allPossibleBST(start, i - 1, memo);
      List<TreeNode> rightSubTrees = allPossibleBST(i + 1, end, memo);

      for (TreeNode left : leftSubTrees) {
        for (TreeNode right : rightSubTrees) {
          TreeNode root = new TreeNode(i, left, right);
          res.add(root);
        }
      }
    }
    memo.put(new Pair<>(start, end), res);
    return res;
  }

  public List<TreeNode> generateTrees(int n) {
      Map<Pair<Integer, Integer>, List<TreeNode>> memo = new HashMap<>();
      return allPossibleBST(1, n, memo);
  }
}
