/* Given the root of a binary tree, return the postorder traversal of its nodes' values.
 * 
 * https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/930/
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
public class BinaryTreePostorderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
      List<Integer> result = new ArrayList<>();

      if (root == null) {
        return result;
      }

      TreeNode previousNode = null;
      Stack<TreeNode> traversalStack = new Stack<>();

      while (root != null || !traversalStack.isEmpty()) {
        if (root != null) {
          traversalStack.push(root);
          root = root.left;
        } else {
          root = traversalStack.peek();

          if (root.right == null || root.right == previousNode) {
            result.add(root.val);
            traversalStack.pop();
            previousNode = root;
            root = null;
          } else {
            root = root.right;
          }
        }
      }

      return result;
    }
}
