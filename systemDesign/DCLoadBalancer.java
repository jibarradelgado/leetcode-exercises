/* Design a simple load distributor for a data center that can do the following:

Add and remove machines from the cluster.
Add applications to run on a machine.
Stop applications that are running on a machine.
Return a list of the applications running on a machine.
Implement the DCLoadBalancer class:

DCLoadBalancer() Initializes the object.
void addMachine(int machineId, int capacity) Registers a machine with the given machineId and maximum capacity.
void removeMachine(int machineId) Removes the machine with the given machineId. All applications running on this machine are automatically reallocated to other machines in the same order as they were added to this machine. The applications should be reallocated in the same manner as addApplication.
int addApplication(int appId, int loadUse) Allocates an application with the given appId and loadUse to the machine with the largest remaining capacity that can handle the additional request. If there is a tie, the machine with the lowest ID is used. Returns the machine ID that the application is allocated to. If no machine can handle the request, return -1.
void stopApplication(int appId) Stops and removes the application with the given appId from the machine it is running on, freeing up the machine's capacity by its corresponding loadUse. If the application does not exist, nothing happens.
List<Integer> getApplications(int machineId) Returns a list of application IDs running on a machine with the given machineId in the order in which they were added. If there are more than 10 applications, return only the first 10 IDs.


https://leetcode.com/explore/learn/card/system-design/689/system-design-basics/4409/
*/

package systemDesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DCLoadBalancer {
    private static class Machine {
      int id;
      int capacity;
      int remaining;
      int version;
      
      LinkedHashMap<Integer, Integer> apps = new LinkedHashMap<>();
      
      Machine(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.remaining = capacity;
        this.version = 0;
      }
    }
  
    private static class HeapNode {
      int machineId;
      int remaining;
      int version;
      
      HeapNode(int machineId, int remaining, int version) {
        this.machineId = machineId;
        this.remaining = remaining; 
        this.version = version;
      }
    }
  
    private final Map<Integer, Machine> machines = new HashMap<>();
  
    private final Map<Integer, int[]> appIndex = new HashMap<>();
  
    private final PriorityQueue<HeapNode> pq = new PriorityQueue<>(
      (a, b) -> {
        if (a.remaining != b.remaining) return b.remaining - a.remaining;
        return Integer.compare(a.machineId, b.machineId);
      }
    );
  
    public DCLoadBalancer() {}
    
    public void addMachine(int machineId, int capacity) {
      if (machines.containsKey(machineId)) return;
      Machine m = new Machine(machineId, capacity);
      machines.put(machineId, m);
      pq.offer(new HeapNode(machineId, m.remaining, m.version));
    }
    
    public void removeMachine(int machineId) {
      Machine m = machines.remove(machineId);
      if (m == null) return;
      
      List<Map.Entry<Integer, Integer>> toReallocate = new ArrayList<>(m.apps.entrySet());
      for (Map.Entry<Integer, Integer> e : toReallocate) {
        int appId = e.getKey();
        appIndex.remove(appId);
      }
      
      for (Map.Entry<Integer, Integer> e : toReallocate) {
        int appId = e.getKey();
        int loadUse = e.getValue();
        addApplication(appId, loadUse);
      }
    }
    
    public int addApplication(int appId, int loadUse) {
      if (appIndex.containsKey(appId)) {
        int[] info = appIndex.get(appId);
        return info[0];
      }
      
      Machine best = peekValidTop();
      if (best == null || best.remaining < loadUse) {
        return -1;
      }
      
      best.apps.put(appId, loadUse);
      best.remaining -= loadUse;
      best.version++;
      appIndex.put(appId, new int[]{best.id, loadUse});
      
      pq.offer(new HeapNode(best.id, best.remaining, best.version));
      return best.id;
    }
    
    public void stopApplication(int appId) {
      int[] info = appIndex.remove(appId);
      if (info == null) return;
      int machineId = info[0], loadUse = info[1];
      
      Machine m = machines.get(machineId);
      if (m == null) return;
      
      Integer removed = m.apps.remove(appId);
      if (removed == null) return;
      m.remaining += loadUse;
      m.version++;
      pq.offer(new HeapNode(m.id, m.remaining, m.version));
    }
    
    public List<Integer> getApplications(int machineId) {
      Machine m = machines.get(machineId);
      if (m == null) return Collections.emptyList();
      List<Integer> ans = new ArrayList<>(10);
      int cnt = 0;
      for (Integer appId : m.apps.keySet()) {
        ans.add(appId);
        if (++cnt == 10) break;
      }
      return ans;
    }
  
    private Machine peekValidTop() {
      while (!pq.isEmpty()) {
        HeapNode top = pq.peek();
        Machine m = machines.get(top.machineId);
        if (m == null || m.version != top.version || m.remaining != top.remaining) {
          pq.poll();
          continue;
        }
        return m;
      }
      return null;
    }
}
