/* Implement a Log Aggregation System which aggregates logs from various services in a datacenter and provides search APIs.

Design the LogAggregator class:

LogAggregator(int machines, int services) Initializes the object with machines and services representing the number of machines and services in the datacenter, respectively.
void pushLog(int logId, int machineId, int serviceId, String message) Adds a log with id logId notifying that the machine machineId sent a string message while executing the service serviceId.
List<Integer> getLogsFromMachine(int machineId) Returns a list of ids of all logs added by machine machineId.
List<Integer> getLogsOfService(int serviceId) Returns a list of ids of all logs added while running service serviceId on any machine.
List<String> search(int serviceId, String searchString) Returns a list of messages of all logs added while running service serviceId where the message of the log contains searchString as a substring.
Note that:

The entries in each list should be in the order they were added, i.e., the older logs should precede the newer logs.
A machine can run multiple services more than once. Similarly, a service can be run on multiple machines.
All logId may not be ordered.
A substring is a contiguous sequence of characters within a string.

https://leetcode.com/explore/learn/card/system-design/689/system-design-basics/4406/
*/

package systemDesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogAggregator {
    private static class Entry {
        final int logId;
        final String message;
        Entry(int logId, String message) {
            this.logId = logId;
            this.message = message;
        }
    }
    
    private final List<Integer>[] machineLogs;
    private final List<Entry>[] serviceLogs;
    
    public LogAggregator(int machines, int services) {
        machineLogs = new ArrayList[machines + 1];
        serviceLogs = new ArrayList[services + 1];
    }
    
    public void pushLog(int logId, int machineId, int serviceId, String message) {
        if (machineLogs[machineId] == null) machineLogs[machineId] = new ArrayList<>();
        machineLogs[machineId].add(logId);
        
        if (serviceLogs[serviceId] == null) serviceLogs[serviceId] = new ArrayList<>();
        serviceLogs[serviceId].add(new Entry(logId, message));
    }
    
    public List<Integer> getLogsFromMachine(int machineId) {
        List<Integer> list = machineLogs[machineId];
        return list == null ? Collections.emptyList() : new ArrayList<>(list);
    }
    
    public List<Integer> getLogsOfService(int serviceId) {
        List<Entry> entries = serviceLogs[serviceId];
        if (entries == null) return Collections.emptyList();
        List<Integer> ids = new ArrayList<>(entries.size());
        for (Entry e : entries) ids.add(e.logId);
        return ids;
    }
    
    public List<String> search(int serviceId, String searchString) {
        List<Entry> entries = serviceLogs[serviceId];
        if (entries == null) return Collections.emptyList();
        List<String> out = new ArrayList<>();
        for (Entry e : entries) {
            if (e.message.contains(searchString)) out.add(e.message);
        }
        return out;
    }
}
