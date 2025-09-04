/* Design a logger system that receives a stream of messages along with their timestamps. Each unique message should only be printed at most every 10 seconds (i.e. a message printed at timestamp t will prevent other identical messages from being printed until timestamp t + 10).

All messages will come in chronological order. Several messages may arrive at the same timestamp.

Implement the Logger class:

Logger() Initializes the logger object.
bool shouldPrintMessage(int timestamp, string message) Returns true if the message should be printed in the given timestamp, otherwise returns false.

https://leetcode.com/explore/learn/card/hash-table/184/comparison-with-other-data-structures/1122/
*/

package hashTable;

class Logger {

  private final Map<String, Integer> nextAllowed;

  public Logger() {
    this.nextAllowed = new HashMap<>();  
  }
  
  public boolean shouldPrintMessage(int timestamp, String message) {
    Integer allowAt = nextAllowed.get(message);
    if (allowAt == null || timestamp >= allowAt) {
      nextAllowed.put(message, timestamp + 10);
      return true;
    }
    return false;
  }
}
