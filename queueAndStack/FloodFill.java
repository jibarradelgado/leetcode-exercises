/* You are given an image represented by an m x n grid of integers image, where image[i][j] represents the pixel value of the image. You are also given three integers sr, sc, and color. Your task is to perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill:

Begin with the starting pixel and change its color to color.
Perform the same process for each pixel that is directly adjacent (pixels that share a side with the original pixel, either horizontally or vertically) and shares the same color as the starting pixel.
Keep repeating this process by checking neighboring pixels of the updated pixels and modifying their color if it matches the original color of the starting pixel.
The process stops when there are no more adjacent pixels of the original color to update.
Return the modified image after performing the flood fill.

https://leetcode.com/explore/learn/card/queue-stack/239/conclusion/1393/
*/

package queueAndStack;

public class FloodFill {
  public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
    int color = image[sr][sc];
    if (color != newColor) {
      dfs(image, sr, sc, color, newColor);
    }
    return image;
  }

  private void dfs(int[][] image, int r, int c, int color, int newColor) {
    if (image[r][c] == color) {
      image[r][c] = newColor;
      if (r >= 1) {
        dfs(image, r - 1, c, color, newColor);
      }
      if (c >= 1) {
        dfs(image, r, c - 1, color, newColor);
      }
      if (r + 1 < image.length) {
        dfs(image, r + 1, c, color, newColor);
      }
      if (c + 1 < image[0].length) {
        dfs(image, r, c + 1, color, newColor);
      }
    }
  }
}
