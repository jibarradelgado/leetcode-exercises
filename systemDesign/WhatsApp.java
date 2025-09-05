/* Design a system like Whatsapp with the following features:

Send a message to a user.
Create a group with some initial users.
Add more users to a group.
Send a message to a group.
Get messages for a user.
Implement the WhatsApp class:

WhatsApp() Initializes the object.
void sendMessage(int toUser, String message) Sends a personal message with the text message to the user with id: toUser.
int createGroup(int[] initialUsers) Creates a new group that initially contains users whose ids are in the list initialUsers, and the group id is returned. For each group created, increment the ids sequentially. For the first group to be created id = 1, for the second group id = 2, and so on.
void addUserToGroup(int groupId, int userId) Adds the user with id: userId to the group with id: groupId. This call should be ignored if the user is already in that group, or if the group does not exist.
void sendGroupMessage(int fromUser, int groupId, String message) Sends a message with the text message by the user with id: fromUser to the group with id: groupId. The message should be sent to all members of the group except the sender. Users added afterwards to the group should not receive the message. Also, this call should be ignored if the user is not a part of the group, or if the group does not exist.
List<String> getMessagesForUser(int userId) Returns all the personal and group messages that were sent to the user with id: userId ordered by the latest ones first.

https://leetcode.com/explore/learn/card/system-design/690/system-design-case-studies/4395/
*/

package systemDesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WhatsApp {
  private final Map<Integer, List<String>> inbox = new HashMap<>();
  private final Map<Integer, Set<Integer>> groups = new HashMap<>();
  private int nextGroupId = 1;

  public WhatsApp() {}

  public void sendMessage(int toUser, String message) {
    inbox.computeIfAbsent(toUser, k -> new ArrayList<>()).add(message);
  }

  public int createGroup(int[] initialUsers) {
    int gid = nextGroupId++;
    Set<Integer> members = new HashSet<>();
    for (int uid : initialUsers) {
      members.add(uid);
    }
    groups.put(gid, members);
    return gid;
  }

  public void addUserToGroup(int groupId, int userId) {
    Set<Integer> members = groups.get(groupId);
    if (members == null) return;
    members.add(userId);
  }

  public void sendGroupMessage(int fromUser, int groupId, String message) {
    Set<Integer> members = groups.get(groupId);
    if (members == null || !members.contains(fromUser)) return;
    for (int uid : members) {
      if (uid == fromUser) {
        continue;
      }
      inbox.computeIfAbsent(uid, k -> new ArrayList<>()).add(message);
    }
  }

  public List<String> getMessagesForUser(int userId) {
    List<String> messages = inbox.get(userId);
    if (messages == null || messages.isEmpty()) return Collections.emptyList();
    List<String> out = new ArrayList<>(messages.size());
    for (int i = messages.size() - 1; i >= 0; i--) {
      out.add(messages.get(i));
    }
    return out;
  }
}
