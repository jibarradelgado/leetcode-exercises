/* You are given an array of integers stones where stones[i] is the weight of the ith stone.

We are playing a game with the stones. On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:

If x == y, both stones are destroyed, and
If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
At the end of the game, there is at most one stone left.

Return the weight of the last remaining stone. If there are no stones left, return 0.

https://leetcode.com/explore/learn/card/heap/646/practices/4084/
*/

package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LastStoneWeight {
  public int lastStoneWeight(int[] stones) {
    PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
    for (int stone : stones) {
      heap.add(stone);
    }

    while (heap.size() > 1) {
      int stone1 = heap.remove();
      int stone2 = heap.remove();
      if (stone1 != stone2) {
        heap.add(stone1 - stone2);
      }
    }

    return heap.isEmpty() ? 0 : heap.remove();
  }
}
