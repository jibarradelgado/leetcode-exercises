/* There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.

https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1391/
*/


package queueAndStack;

import java.util.List;
import java.util.Stack;

public class KeyAndRooms {
  public boolean canVisitAllRooms(List<List<Integer>> rooms) {
    boolean[] seen = new boolean[rooms.size()];
    seen[0] = true;
    Stack<Integer> stack = new Stack<>();
    stack.push(0);

    while (!stack.isEmpty()) {
      int node = stack.pop();
      for (int nei : rooms.get(node)) {
        if (!seen[nei]) {
          seen[nei] = true;
          stack.push(nei);
        }
      }
    }

    for (boolean v : seen) {
      if (!v) return false;
    }
    return true;
  }
}
