/* Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

https://leetcode.com/explore/learn/card/heap/646/practices/4088/
*/

package heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class KClosestPointsToOrigin {
  public int[][] kClosest(int[][] points, int k) {
    Queue<int[]> maxPQ = new PriorityQueue<>((a,b) -> b[0] - a[0]);
    for (int i = 0; i < points.length; i++) {
      int[] entry = {squaredDistance(points[i]), i};
      if (maxPQ.size() < k) {
        maxPQ.add(entry);
      } else if (entry[0] < maxPQ.peek()[0]) {
          maxPQ.poll();
          maxPQ.add(entry);
      }
    }

    int[][] answer = new int[k][2];
    for (int i = 0; i < k; i++) {
      int entryIndex = maxPQ.poll()[1];
      answer[i] = points[entryIndex];
    }
    return answer;
  }

  private int squaredDistance(int[] point) {
    return point[0] * point[0] + point[1] * point[1]; 
  }
}
