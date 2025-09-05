/* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/995/
*/

package binaryTree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

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
public class SerializeAndDeserializeBinaryTree {
    public String serialize(TreeNode root) {
      StringBuilder sb = new StringBuilder();
      serializePre(root, sb);
      return sb.toString();
    }

    private void serializePre(TreeNode node, StringBuilder sb) {
      if (node == null) {
        sb.append("#").append(",");
        return;
      }
      sb.append(node.val).append(",");
      serializePre(node.left, sb);
      serializePre(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
      Deque<String> q = new ArrayDeque<>(Arrays.asList(data.split(",")));
      return build(q);
    }

    private TreeNode build(Deque<String> q) {
      if (q.isEmpty()) return null;
      String token = q.pollFirst();
      if (token.equals("#") || token.isEmpty()) {
        return token.equals("#") ? null : build(q);
      }
      TreeNode node = new TreeNode(Integer.parseInt(token));
      node.left = build(q);
      node.right = build(q);
      return node;
    }
}
