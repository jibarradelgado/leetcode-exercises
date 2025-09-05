/* Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/932/
*/

package binaryTree;

import javax.swing.tree.TreeNode;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class LowestCommonAncestorOfABinaryTree {
  private TreeNode ans;

  public Solution() {
    this.ans = null;
  }

  private boolean recurseTree(TreeNode currentNode, TreeNode p, TreeNode q) {
    if (currentNode == null) {
      return false;
    }

    int left = this.recurseTree(currentNode.left, p, q) ? 1 : 0;
    int right = this.recurseTree(currentNode.right, p, q) ? 1 : 0;
    int mid = (currentNode == p || currentNode == q) ? 1 : 0;

    if (mid + left + right >= 2) {
      this.ans = currentNode;
    }

    return (mid + left + right > 0);
  }

  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
      this.recurseTree(root, p, q);
      return this.ans;
  }
}
