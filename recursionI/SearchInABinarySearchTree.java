/* You are given the root of a binary search tree (BST) and an integer val.

Find the node in the BST that the node's value equals val and return the subtree rooted with that node. If such a node does not exist, return null.

https://leetcode.com/explore/learn/card/recursion-i/251/scenario-i-recurrence-relation/3233/
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
public class SearchInABinarySearchTree {
    public TreeNode searchBST(TreeNode root, int val) {
      if (root == null || val == root.val) return root;

      return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }
}
