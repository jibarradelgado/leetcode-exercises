/* Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * 
 * https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/929/
 */

package binaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
public class BinaryTreeInorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> res = new ArrayList<>();
      Stack<TreeNode> stack = new Stack<>();
      TreeNode curr = root;
      while (curr != null || !stack.isEmpty()) {
          while (curr != null) {
              stack.push(curr);
              curr = curr.left;
          }
          curr = stack.pop();
          res.add(curr.val);
          curr = curr.right;
      }
      return res;
    }
}
