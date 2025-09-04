/* Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

https://leetcode.com/explore/learn/card/recursion-i/256/complexity-analysis/2375/
*/

package recursionI;

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
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
      if(root == null) {
        return 0;
      }
      
      int leftDepth = maxDepth(root.left);
      int rightDepth = maxDepth(root.right);
      
      return Math.max(leftDepth, rightDepth) + 1;
    }
}
