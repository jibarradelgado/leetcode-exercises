/* Given the root of a binary tree, return all duplicate subtrees.

For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with the same node values.

https://leetcode.com/explore/learn/card/hash-table/185/hash_table_design_the_key/1127/
*/

package hashTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
public class FindDuplicateSubtrees {
  public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
    List<TreeNode> res = new LinkedList<>();
    traverse(root, new HashMap<>(), res);
    return res;
  }

  public String traverse(TreeNode node, Map<String, Integer> cnt, List<TreeNode> res) {
    if (node == null) {
      return "";
    }
    String representation = "(" + traverse(node.left, cnt, res) + ")" + node.val + "(" + traverse(node.right, cnt, res) + ")";
    cnt.put(representation, cnt.getOrDefault(representation, 0) + 1);
    if (cnt.get(representation) == 2) {
      res.add(node);
    }
    return representation;
  }
}
