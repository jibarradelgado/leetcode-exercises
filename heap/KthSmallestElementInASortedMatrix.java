/* Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).

https://leetcode.com/explore/learn/card/heap/646/practices/4086/
*/

package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

class MyHeapNode {
  int row;
  int column;
  int value;

  public MyHeapNode(int v, int r, int c) {
    this.value = v;
    this.row = r;
    this.column = c;
  }

  public int getValue() {
    return this.value;
  }

  public int getRow() {
    return this.row;
  }

  public int getColumn() {
    return this.column;
  }
}

class MyHeapComparator implements Comparator<MyHeapNode> {
  public int compare(MyHeapNode x, MyHeapNode y) {
    return x.value - y.value;
  }
}

public class KthSmallestElementInASortedMatrix {
  public int kthSmallest(int[][] matrix, int k) {
    int N = matrix.length;

    PriorityQueue<MyHeapNode> minHeap = 
      new PriorityQueue<MyHeapNode>(Math.min(N, k), new MyHeapComparator());

    for (int r = 0; r < Math.min(N, k); r++) {
      minHeap.offer(new MyHeapNode(matrix[r][0], r, 0));
    }

    MyHeapNode element = minHeap.peek();
    while (k-- > 0) {
      element = minHeap.poll();
      int r = element.getRow(), c = element.getColumn();

      if (c < N - 1) {
        minHeap.offer(new MyHeapNode(matrix[r][c + 1], r, c + 1));
      }
    }

    return element.getValue();
  }
}
