/* You have some number of sticks with positive integer lengths. These lengths are given as an array sticks, where sticks[i] is the length of the ith stick.

You can connect any two sticks of lengths x and y into one stick by paying a cost of x + y. You must connect all the sticks until there is only one stick remaining.

Return the minimum cost of connecting all the given sticks into one stick in this way.

https://leetcode.com/explore/learn/card/heap/646/practices/4090/
*/

package heap;

import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {
  public int connectSticks(int[] sticks) {
    int totalCost = 0;

    PriorityQueue<Integer> pq = new PriorityQueue<>();

    for (int stick : sticks) {
      pq.add(stick);
    }

    while (pq.size() > 1) {
      int stick1 = pq.remove();
      int stick2 = pq.remove();

      int cost = stick1 + stick2;
      totalCost += cost;

      pq.add(stick1 + stick2);
    }

    return totalCost;
  }
}
