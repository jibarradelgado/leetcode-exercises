/* Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.

Implement the Twitter class:

Twitter() Initializes your twitter object.
void postTweet(int userId, int tweetId) Composes a new tweet with ID tweetId by the user userId. Each call to this function will be made with a unique tweetId.
List<Integer> getNewsFeed(int userId) Retrieves the 10 most recent tweet IDs in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user themself. Tweets must be ordered from most recent to least recent.
void follow(int followerId, int followeeId) The user with ID followerId started following the user with ID followeeId.
void unfollow(int followerId, int followeeId) The user with ID followerId started unfollowing the user with ID followeeId.

https://leetcode.com/explore/learn/card/system-design/690/system-design-case-studies/4413/
*/


package systemDesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Twitter {
  private static class Tweet {
    int id, time;
    Tweet(int id, int time) { this.id = id; this.time = time; }
  }
  
  private static class Node {
    int userId, idx;
    Node(int u, int i) { userId = u; idx = i; }
  }
  
  private final Map<Integer, List<Tweet>> posts = new HashMap<>();
  private final Map<Integer, Set<Integer>> follows = new HashMap<>();
  private int clock = 0;
  
  public Twitter() {}
  
  public void postTweet(int userId, int tweetId) {
    posts.computeIfAbsent(userId, k -> new ArrayList<>()).add(new Tweet(tweetId, ++clock));
  }
  
  public List<Integer> getNewsFeed(int userId) {
    Set<Integer> src = new HashSet<>();
    src.add(userId);
    Set<Integer> fset = follows.get(userId);
    if (fset != null) src.addAll(fset);
    
    PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
      Tweet ta = posts.get(a.userId).get(a.idx);
      Tweet tb = posts.get(b.userId).get(b.idx);
      return Integer.compare(tb.time, ta.time);
    });
    
    for (int u : src) {
      List<Tweet> list = posts.get(u);
      if (list != null && !list.isEmpty()) {
        pq.offer(new Node(u, list.size() - 1));
      }
    }
    
    List<Integer> feed = new ArrayList<>(10);
    while (!pq.isEmpty() && feed.size() < 10) {
      Node top =  pq.poll();
      Tweet tw = posts.get(top.userId).get(top.idx);
      feed.add(tw.id);
      if (top.idx - 1 >= 0) {
        pq.offer(new Node(top.userId, top.idx - 1));
      }
    }
    return feed;
  }
  
  public void follow(int followerId, int followeeId) {
    if (followerId == followeeId) return;
    follows.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
  }
  
  public void unfollow(int followerId, int followeeId) {
    Set<Integer> s = follows.get(followerId);
    if (s != null) s.remove(followeeId);
  }
}
