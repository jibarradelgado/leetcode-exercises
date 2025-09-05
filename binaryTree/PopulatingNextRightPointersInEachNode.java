/* You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

https://leetcode.com/explore/learn/card/data-structure-tree/133/conclusion/994/
*/

package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
public class PopulatingNextRightPointersInEachNode {
  public Node connect(Node root) {
    if (root == null) {
      return root;
    } 

    Queue<Node> Q = new LinkedList<Node>();
    Q.add(root);

    while (Q.size() > 0) {
      int size = Q.size();

      for (int i = 0; i < size; i++) {
        Node node = Q.poll();

        if (i < size - 1) {
          node.next = Q.peek();
        }

        if (node.left != null) {
          Q.add(node.left);
        }
        if (node.right != null) {
          Q.add(node.right);
        }
      }
    }

    return root;
  }
}
