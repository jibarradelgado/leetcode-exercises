/* Design a simple dating system like Tinder with the following features:

Register a user with their gender, age, preferences, and interests.
Find matching users according to their preferred gender, preferred age range, and common interests.
Implement the Tinder class:

Tinder() Initializes the object.
void signup(int userId, int gender, int preferredGender, int age, int minPreferredAge, int maxPreferredAge, List<String> interests) Registers a user with the given attributes.
List<Integer> getMatches(int userId) Returns the top 5 matches for the given user. The returned matches should satisfy the following:
The returned user's gender should equal the given user's preferredGender.
The returned user's age should be between the given user's minPreferredAge and maxPreferredAge (inclusive).
There should be at least 1 common interest between the returned user and the given user.
The results should be sorted in decreasing order by the number of common interests. If there is a tie, it should be sorted in increasing order by userId.
If there are fewer than 5 matches, return as many as possible.
Note that the given user might not necessarily be a match for the returned users.

https://leetcode.com/explore/learn/card/system-design/690/system-design-case-studies/4412/
*/

package systemDesign;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Tinder {
  private static class User {
    final int id;
    final int gender;
    final int preferredGender;
    final int age;
    final int minPreferredAge;
    final int maxPreferredAge;
    final Set<String> interests;

    User(int id, int gender, int preferredGender, int age, int minPreferredAge, int maxPreferredAge, List<String> interests) {
      this.id = id;
      this.gender = gender;
      this.preferredGender = preferredGender;
      this.age = age;
      this.minPreferredAge = minPreferredAge;
      this.maxPreferredAge = maxPreferredAge;
      this.interests = new HashSet<>(interests);
    }
  }

  private final Map<Integer, User> users = new HashMap<>();

  public Tinder() {}

  public void signup(int userId, int gender, int preferredGender, int age, int minPreferredAge, int maxPreferredAge, List<String> interests) {
    users.put(userId, new User(userId, gender, preferredGender, age, minPreferredAge, maxPreferredAge, interests));
  }

  public List<Integer> getMatches(int userId) {
    User me = users.get(userId);
    if (me == null) return Collections.emptyList();

    List<int[]> candidates = new ArrayList<>();

    for (User u : users.values()) {
      if (u.id == me.id) continue;

      if (u.gender != me.preferredGender) continue;
      if (u.age < me.minPreferredAge ||  u.age > me.maxPreferredAge) continue;

      int common = 0;
      for (String s : me.interests) {
        if (u.interests.contains(s)) {
          common++;
        }
      }
      if (common >= 1) {
        candidates.add(new int[]{common, u.id});
      }
    }

    if (candidates.isEmpty()) return Collections.emptyList();

    candidates.sort((a, b) -> {
      if (a[0] != b[0]) return b[0] - a[0];
      return a[1] - b[1];
    });
    
    List<Integer> ans = new ArrayList<>(Math.min(5, candidates.size()));
    for (int i = 0; i < candidates.size() && i < 5; i++) {
      ans.add(candidates.get(i)[1]);
    }
    return ans;
  }
}
