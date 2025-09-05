/* You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.

The lock initially starts at '0000', a string representing the state of the 4 wheels.

You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.

Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1375/
*/

package queueAndStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class OpenTheLock {
    public int openLock(String[] deadends, String target) {
      Map<Character, Character> nextSlot = Map.of(
        '0', '1', 
        '1', '2', 
        '2', '3', 
        '3', '4', 
        '4', '5', 
        '5', '6', 
        '6', '7', 
        '7', '8', 
        '8', '9', 
        '9', '0'
      );
      Map<Character, Character> prevSlot = Map.of(
        '0', '9', 
        '1', '0', 
        '2', '1', 
        '3', '2', 
        '4', '3', 
        '5', '4', 
        '6', '5', 
        '7', '6', 
        '8', '7', 
        '9', '8'
      );

      Set<String> visitedCombinations = new HashSet<>(Arrays.asList(deadends));
      Queue<String> pendingCombinations = new LinkedList<String>();

      int turns = 0;

      if (visitedCombinations.contains("0000")) {
        return -1;
      }

      pendingCombinations.add("0000");
      visitedCombinations.add("0000");

      while (!pendingCombinations.isEmpty()) {
        int currLevelNodesCount = pendingCombinations.size();
        for (int i = 0; i < currLevelNodesCount; i++) {
          String currentCombination = pendingCombinations.poll();

          if (currentCombination.equals(target)) {
            return turns;
          }

          for (int wheel = 0; wheel < 4; wheel += 1) {
            StringBuilder newCombination = new StringBuilder(currentCombination);
            newCombination.setCharAt(wheel, nextSlot.get(newCombination.charAt(wheel)));

            if (!visitedCombinations.contains(newCombination.toString())) {
              pendingCombinations.add(newCombination.toString());
              visitedCombinations.add(newCombination.toString());
            }

            newCombination = new StringBuilder(currentCombination);
            newCombination.setCharAt(wheel, prevSlot.get(newCombination.charAt(wheel)));

            if (!visitedCombinations.contains(newCombination.toString())) {
              pendingCombinations.add(newCombination.toString());
              visitedCombinations.add(newCombination.toString());
            }
          }
        }
        turns += 1;
      }
      return -1;
    }
}
