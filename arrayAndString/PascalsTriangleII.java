/* Given an integer rowIndex, return the rowIndexth (0-indexed) row of the Pascal's triangle.

In Pascal's triangle, each number is the sum of the two numbers directly above it 

https://leetcode.com/explore/learn/card/array-and-string/204/conclusion/1171/
*/

package arrayAndString;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleII {
    public List<Integer> getRow(int rowIndex) {
      List<Integer> row = new ArrayList<Integer>(rowIndex + 1) {
        {
          add(1);
        }
      };

      for (int i = 0; i < rowIndex; i++) {
        for (int j = i; j > 0; j--) {
          row.set(j, row.get(j) + row.get(j - 1));
        }
        row.add(1);
      }

      return row;
    }
}
