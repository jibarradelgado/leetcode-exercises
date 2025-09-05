/* Design a Todo List Where users can add tasks, mark them as complete, or get a list of pending tasks. Users can also add tags to tasks and can filter the tasks by certain tags.

Implement the TodoList class:

TodoList() Initializes the object.
int addTask(int userId, String taskDescription, int dueDate, List<String> tags) Adds a task for the user with the ID userId with a due date equal to dueDate and a list of tags attached to the task. The return value is the ID of the task. This ID starts at 1 and is sequentially increasing. That is, the first task's id should be 1, the second task's id should be 2, and so on.
List<String> getAllTasks(int userId) Returns a list of all the tasks not marked as complete for the user with ID userId, ordered by the due date. You should return an empty list if the user has no uncompleted tasks.
List<String> getTasksForTag(int userId, String tag) Returns a list of all the tasks that are not marked as complete for the user with the ID userId and have tag as one of their tags, ordered by their due date. Return an empty list if no such task exists.
void completeTask(int userId, int taskId) Marks the task with the ID taskId as completed only if the task exists and the user with the ID userId has this task, and it is uncompleted.

https://leetcode.com/explore/learn/card/system-design/691/practice-problems/4727/
*/

package systemDesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ToDoList {
    // ----- Task model -----
    private static class Task {
        final int id;
        final int userId;
        final String desc;
        final int dueDate;        // unique across all tasks (per constraints)
        final List<String> tags;  // original order not needed; we use as set for indexing
        boolean completed;

        Task(int id, int userId, String desc, int dueDate, List<String> tags) {
            this.id = id;
            this.userId = userId;
            this.desc = desc;
            this.dueDate = dueDate;
            this.tags = tags == null ? Collections.emptyList() : new ArrayList<>(tags);
            this.completed = false;
        }
    }

    // ----- Data structures -----
    private int nextId = 1;

    // All tasks by id (to validate user & status on completion)
    private final Map<Integer, Task> tasksById = new HashMap<>();

    // For each user: pending tasks sorted by dueDate
    // userId -> TreeMap<dueDate, Task>
    private final Map<Integer, TreeMap<Integer, Task>> pendingByUser = new HashMap<>();

    // For each user: for each tag: pending tasks sorted by dueDate
    // userId -> (tag -> TreeMap<dueDate, Task>)
    private final Map<Integer, Map<String, TreeMap<Integer, Task>>> pendingByUserTag = new HashMap<>();

    public ToDoList() {}

    public int addTask(int userId, String taskDescription, int dueDate, List<String> tags) {
        int id = nextId++;
        Task t = new Task(id, userId, taskDescription, dueDate, tags);
        tasksById.put(id, t);

        // index in per-user pending
        pendingByUser
            .computeIfAbsent(userId, k -> new TreeMap<>())
            .put(dueDate, t);

        // index in per-user-per-tag pending
        if (t.tags != null) {
            for (String tag : t.tags) {
                pendingByUserTag
                    .computeIfAbsent(userId, k -> new HashMap<>())
                    .computeIfAbsent(tag, k -> new TreeMap<>())
                    .put(dueDate, t);
            }
        }
        return id;
    }

    public List<String> getAllTasks(int userId) {
        TreeMap<Integer, Task> tm = pendingByUser.get(userId);
        if (tm == null || tm.isEmpty()) return Collections.emptyList();
        List<String> out = new ArrayList<>(tm.size());
        for (Task t : tm.values()) {
            out.add(t.desc);
        }
        return out;
    }

    public List<String> getTasksForTag(int userId, String tag) {
        Map<String, TreeMap<Integer, Task>> tagsMap = pendingByUserTag.get(userId);
        if (tagsMap == null) return Collections.emptyList();
        TreeMap<Integer, Task> tm = tagsMap.get(tag);
        if (tm == null || tm.isEmpty()) return Collections.emptyList();
        List<String> out = new ArrayList<>(tm.size());
        for (Task t : tm.values()) {
            out.add(t.desc);
        }
        return out;
    }

    public void completeTask(int userId, int taskId) {
        Task t = tasksById.get(taskId);
        if (t == null) return;                 // task doesn't exist
        if (t.userId != userId) return;        // not this user's task
        if (t.completed) return;               // already completed

        t.completed = true;

        // remove from per-user pending
        TreeMap<Integer, Task> userMap = pendingByUser.get(userId);
        if (userMap != null) {
            userMap.remove(t.dueDate);
            if (userMap.isEmpty()) pendingByUser.remove(userId);
        }

        // remove from per-user-per-tag pending
        if (t.tags != null && !t.tags.isEmpty()) {
            Map<String, TreeMap<Integer, Task>> byTag = pendingByUserTag.get(userId);
            if (byTag != null) {
                for (String tag : t.tags) {
                    TreeMap<Integer, Task> tm = byTag.get(tag);
                    if (tm != null) {
                        tm.remove(t.dueDate);
                        if (tm.isEmpty()) {
                            byTag.remove(tag);
                        }
                    }
                }
                if (byTag.isEmpty()) pendingByUserTag.remove(userId);
            }
        }
    }
}
