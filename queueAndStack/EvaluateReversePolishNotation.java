/* You are given an array of strings tokens that represents an arithmetic expression in a Reverse Polish Notation.

Evaluate the expression. Return an integer that represents the value of the expression.

Note that:

The valid operators are '+', '-', '*', and '/'.
Each operand may be an integer or another expression.
The division between two integers always truncates toward zero.
There will not be any division by zero.
The input represents a valid arithmetic expression in a reverse polish notation.
The answer and all the intermediate calculations can be represented in a 32-bit integer.

https://leetcode.com/explore/learn/card/queue-stack/230/usage-stack/1394/
*/

package queueAndStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class EvaluateReversePolishNotation {
  private static final Map<
    String,
    BiFunction<Integer, Integer, Integer>
  > OPERATIONS = new HashMap<>();

  static {
    OPERATIONS.put("+", (a, b) -> a + b);
    OPERATIONS.put("-", (a, b) -> a - b);
    OPERATIONS.put("*", (a, b) -> a * b);
    OPERATIONS.put("/", (a, b) -> a / b);
  }

  public int evalRPN(String[] tokens) {
    Stack<Integer> stack = new Stack<>();

    for (String token : tokens) {
      if (!OPERATIONS.containsKey(token)) {
        stack.push(Integer.valueOf(token));
        continue;
      }

      int number2 = stack.pop();
      int number1 = stack.pop();
      BiFunction<Integer, Integer, Integer> operation;
      operation = OPERATIONS.get(token);
      int result = operation.apply(number1, number2);
      stack.push(result);
    }

    return stack.pop();
  }
}
