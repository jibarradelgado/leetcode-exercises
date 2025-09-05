/* Given the root of a binary search tree and a target value, return the value in the BST that is closest to the target. If there are multiple answers, print the smallest.
 * 
 * 
 * https://leetcode.com/explore/learn/card/binary-search/136/template-analysis/1028/
 */

package binarySearch;

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
public class ClosestBinarySearchTreeValue {
  public int closestValue(TreeNode root, double target) {
    int val, closest = root.val;
    while (root != null) {
      val = root.val;
      closest = Math.abs(val - target) < Math.abs(closest - target) ||
        (Math.abs(val - target) == Math.abs(closest - target) && val < closest) ?
        val : closest;
      root = target < root.val ? root.left : root.right;
    }
    return closest;
  }
}
