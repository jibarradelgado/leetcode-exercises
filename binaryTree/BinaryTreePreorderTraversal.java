/* Given the root of a binary tree, return the preorder traversal of its nodes' values.
 * 
 * https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/928/
 */

package binaryTree;

import java.util.LinkedList;
import java.util.List;

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
public class BinaryTreePreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
      LinkedList<TreeNode> stack = new LinkedList<>();
      LinkedList<Integer> output = new LinkedList<>();
      if (root == null) {
          return output;
      }

      stack.add(root);
      while (!stack.isEmpty()) {
          TreeNode node = stack.pollLast();
          output.add(node.val);
          if (node.right != null) {
              stack.add(node.right);
          }
          if (node.left != null) {
              stack.add(node.left);
          }
      }
      return output;
    }
}
