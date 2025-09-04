/* Given an integer numRows, return the first numRows of Pascal's triangle.
 * 
 * https://leetcode.com/explore/learn/card/array-and-string/202/introduction-to-2d-array/1170/
 */

package arrayAndString;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
      List<List<Integer>> triangle = new ArrayList<List<Integer>>();

      triangle.add(new ArrayList<>());
      triangle.get(0).add(1);

      for (int rowNum = 1; rowNum < numRows; rowNum++) {
        List<Integer> row = new ArrayList<>();
        List<Integer> prevRow = triangle.get(rowNum - 1);

        row.add(1);

        for (int j = 1; j < rowNum; j++) {
          row.add(prevRow.get(j - 1) + prevRow.get(j));
        }

        row.add(1);

        triangle.add(row);
      }

      return triangle;
    }
}
