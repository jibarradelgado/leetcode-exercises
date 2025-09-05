/* Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.
 * 
 * https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/943/
 */

package binaryTree;

import java.util.HashMap;
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
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
  int preorderIndex;
  Map<Integer, Integer> inorderIndexMap;

  private TreeNode arrayToTree(int[] preorder, int left, int right) {
    if(left > right) return null;

    int rootValue = preorder[preorderIndex++];
    TreeNode root = new TreeNode(rootValue);

    root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
    root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);

    return root;
  }

  public TreeNode buildTree(int[] preorder, int[] inorder) {
    preorderIndex = 0;

    inorderIndexMap = new HashMap<>();
    for (int i = 0; i < inorder.length; i++) {
      inorderIndexMap.put(inorder[i], i);
    }

    return arrayToTree(preorder, 0, preorder.length - 1);
  }
}
