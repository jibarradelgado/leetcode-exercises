/* Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * 
 * https://leetcode.com/explore/learn/card/data-structure-tree/134/traverse-a-tree/931/
 */

package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
      List<List<Integer>> levels = new ArrayList<List<Integer>>();
      if (root == null) return levels;

      Queue<TreeNode> queue = new LinkedList<TreeNode>();
      queue.add(root);
      int level = 0;
      while (!queue.isEmpty()) {
        levels.add(new ArrayList<Integer>());

        int level_length = queue.size();
        for (int i = 0; i < level_length; ++i) {
          TreeNode node = queue.remove();

          levels.get(level).add(node.val);

          if (node.left != null) queue.add(node.left);
          if (node.right != null) queue.add(node.right);
        }

        level++;
      }
      return levels;
    }
}
