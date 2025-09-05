/* Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree, construct and return the binary tree.
 * 
 * https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/942/
 */

package binaryTree;

import java.util.HashMap;

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
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
  int post_idx;
  int[] postorder;
  int[] inorder;
  HashMap<Integer, Integer> idx_map = new HashMap<>();

  public TreeNode helper(int in_left, int in_right) {
    if (in_left > in_right) return null;

    int root_val = postorder[post_idx];
    TreeNode root = new TreeNode(root_val);

    int index = idx_map.get(root_val);

    post_idx--;
    root.right = helper(index + 1, in_right);
    root.left = helper(in_left, index - 1);
    return root;
  }

  public TreeNode buildTree(int[] inorder, int[] postorder) {
    this.postorder = postorder;
    this.inorder = inorder;
    post_idx = postorder.length - 1;

    int idx = 0;
    for (Integer val : inorder) idx_map.put(val, idx++);
    return helper(0, inorder.length - 1);
  }
}
