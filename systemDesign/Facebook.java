/* Design a system like Facebook with the following features:

A user can write a post.
Two users can become friends with each other.
Users can see all the posts written by their friends.
Implement the Facebook class:

Facebook() Initializes the object.
void writePost(int userId, String postContent) The user with id userId writes a post with the content postContent.
void addFriend(int user1, int user2) user1 and user2 become friends with each other. This call should be ignored if user1 and user2 are already friends.
List<String> showPosts(int userId) Returns all the posts made by the friends of the user with id userId ordered by the latest ones first, including ones made before they became friends. Note that the posts made by user userId should not be returned.

https://leetcode.com/explore/learn/card/system-design/690/system-design-case-studies/4396/
*/

package systemDesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Facebook {
  private static class Post {
    final String content;
    final int time;
    Post(String c, int t) { content = c; time = t; }
  }
  
  private final Map<Integer, Set<Integer>> friends = new HashMap<>();
  private final Map<Integer, List<Post>> postsByUser = new HashMap<>();
  private int clock = 0;
  
  public Facebook() {}
  
  public void writePost(int userId, String postContent) {
    postsByUser.computeIfAbsent(userId, k -> new ArrayList<>()).add(new Post(postContent, ++clock));
  }
  
  public void addFriend(int user1, int user2) {
    if (user1 == user2) return;
    friends.computeIfAbsent(user1, k -> new HashSet<>()).add(user2);
    friends.computeIfAbsent(user2, k -> new HashSet<>()).add(user1);
  }
  
  public List<String> showPosts(int userId) {
    Set<Integer> fset = friends.get(userId);
    if (fset == null || fset.isEmpty()) return Collections.emptyList();
    
    List<Post> all = new ArrayList<>();
    for (int f : fset) {
      List<Post> plist = postsByUser.get(f);
      if (plist != null && !plist.isEmpty()) all.addAll(plist);
    }
    
    if (all.isEmpty()) return Collections.emptyList();
    
    all.sort((a, b) -> Integer.compare(b.time, a.time));
    
    List<String> out = new ArrayList<>(all.size());
    for (Post p : all) {
      out.add(p.content);
    }
    return out;
  }
}
