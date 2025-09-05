/* Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

https://leetcode.com/explore/learn/card/queue-stack/231/practical-application-queue/1374/
*/

package queueAndStack;

public class NumberOfIslands {
    public int numIslands(char[][] grid) {
      if(grid == null || grid.length == 0 || grid[0].length == 0) {
        return 0;
      }

      int numIslands = 0;
      int rows = grid.length;
      int cols = grid[0].length;

      for (int i = 0; i < rows; i++) {
        for(int j = 0; j < cols; j++) {
          if(grid[i][j] == '1') {
            numIslands++;
            dfs(grid, i, j);
          }
        }
      }
      return numIslands;
    }

    public void dfs(char[][] grid, int row, int col) {
      int rows = grid.length;
      int cols = grid[0].length;

      if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] != '1' ) {
        return;
      }

      grid[row][col] = '0';

      dfs(grid, row - 1, col);
      dfs(grid, row + 1, col);
      dfs(grid, row, col - 1);
      dfs(grid, row, col + 1);
    }
}
