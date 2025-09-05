/* Implement a Cab Booking Application (like Uber) which facilitates:

Addition of new cabs to the system.
Updating the trips of various customers.
Finding the nearest cabs to a location.
Design the Uber class:

Uber() Initializes the Uber object with 0 cabs and 0 running trips.
void addCab(int cabX, int cabY) Adds a cab located at point (cabX, cabY) to the system. Note that multiple cabs can be at the same location.
int[] startTrip(int customerID, int customerX, int customerY) Returns an integer array [nearX, nearY] where nearX and nearY represent the X-coordinate and Y-coordinate (respectively) of the closest available cab to customer customerID, present at (customerX, customerY). In case there are multiple such cabs, it returns the location of the cab with the smallest X-coordinate, and if there are still multiple choices, it chooses the cab with the smallest Y-coordinate. In case there are no available cabs, returns [-1, -1]. The cab is then assigned to the customer, who starts their trip.
void endTrip(int customerID, int customerX, int customerY) The customer customerID ends their trip at (customerX, customerY). In case a cab was assigned to them by the system, re-adds it back to the system at (customerX, customerY), otherwise ignores the call.
List<List<Integer>> getNearestCabs(int k, int x, int y) Returns a list of locations of the k closest available cabs to (x, y), sorted in non-decreasing order by X-coordinate and subsequently by Y-coordinate. In case there are multiple choices, it chooses the cab with the smaller X-coordinate, and if there are still multiple choices, it chooses the one with the smaller Y-coordinate. In case there are less than k cabs available, it returns the locations of all of them.
Note: The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

https://leetcode.com/explore/learn/card/system-design/690/system-design-case-studies/4499/
*/

package systemDesign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Uber {
  private static class Point {
    final int x, y;
    Point(int x, int y) { this.x = x; this.y = y; }
    @Override public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Point)) return false;
      Point p = (Point) o;
      return x == p.x && y == p.y;
    }
    @Override public int hashCode() {
      return 31 * x + y;
    }
  }
  
  private final Map<Point, Integer> countByLoc = new HashMap<>();
  private final Set<Integer> inTrip = new HashSet<>();
  
  public Uber() {}
  
  public void addCab(int cabX, int cabY) {
    Point p = new Point(cabX, cabY);
    countByLoc.put(p, countByLoc.getOrDefault(p, 0) + 1);
  }
  
  public int[] startTrip(int customerID, int customerX, int customerY) {
    if (inTrip.contains(customerID)) {
      return new int[]{-1,-1};
    }
    if (countByLoc.isEmpty()) return new int[]{-1,-1};
    
    Point best = null;
    long bestD2 = Long.MAX_VALUE;
    
    for (Map.Entry<Point, Integer> e : countByLoc.entrySet()) {
      Point p = e.getKey();
      int cnt = e.getValue();
      if (cnt <= 0) continue;
      long dx = (long)p.x - customerX;
      long dy = (long)p.y - customerY;
      long d2 = dx * dx + dy * dy;
      
      if (best == null ||
          d2 < bestD2 || 
          (d2 == bestD2 && (p.x < best.x || (p.x == best.x && p.y < best.y)))) {
        best = p;
        bestD2 = d2;
      }
    }
    
    if (best == null) return new int[]{-1,-1};
    
    int left = countByLoc.get(best) - 1;
    if (left == 0) countByLoc.remove(best);
    else countByLoc.put(best, left);
    
    inTrip.add(customerID);
    return new int[]{best.x, best.y};
  }
  
  public void endTrip(int customerID, int customerX, int customerY) {
    if (!inTrip.remove(customerID)) return;
    Point p = new Point(customerX, customerY);
    countByLoc.put(p, countByLoc.getOrDefault(p, 0) + 1);
  }
  
  public List<List<Integer>> getNearestCabs(int k, int x, int y) {
    if (countByLoc.isEmpty() || k <= 0) return Collections.emptyList();
    
    PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> {
      if (a[0] != b[0]) return Long.compare(b[0], a[0]);
      if (a[1] != b[1]) return Long.compare(b[1], a[1]);
      return Long.compare(b[2], a[2]);
    });
    
    for (Map.Entry<Point, Integer> e : countByLoc.entrySet()) {
      Point p = e.getKey();
      int cnt = e.getValue();
      
      int repeats = Math.min(cnt, k);
      long dx = (long)p.x - x;
      long dy = (long)p.y - y;
      long d2 = dx * dx + dy * dy;
      
      for (int i = 0; i < repeats; i++) {
        long[] candidate = new long[]{d2, p.x, p.y};
        if (pq.size() < k) {
          pq.offer(candidate);
        } else {
          long[] worst = pq.peek();
          if (compareBetter(candidate, worst)) {
            pq.poll();
            pq.offer(candidate);
          }
        }
      }
    }
    
    if (pq.isEmpty()) return Collections.emptyList();
    
    List<int[]> tmp = new ArrayList<>();
    while(!pq.isEmpty()) {
      long[] a = pq.poll();
      tmp.add(new int[]{(int)a[1], (int)a[2]});
    }
    tmp.sort((u, v) -> u[0] != v[0] ? Integer.compare(u[0], v[0])
                                    : Integer.compare(u[1], v[1]));  
    
    List<List<Integer>> ans = new ArrayList<>(tmp.size());
    for (int[] p : tmp) ans.add(Arrays.asList(p[0], p[1]));
    return ans;
  }
  
  private boolean compareBetter(long[] a, long[] b) {
    if (a[0] != b[0]) return a[0] < b[0];
    if (a[1] != b[1]) return a[1] < b[1];
    return a[2] < b[2];
  }
}
