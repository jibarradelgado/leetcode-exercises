/* Given an array arr, replace every element in that array with the greatest element among the elements to its right, and replace the last element with -1.

After doing so, return the array. */
// https://leetcode.com/explore/learn/card/fun-with-arrays/511/in-place-operations/3259/

package arrays101;

public class ReplaceElementsWithGreatestElementOnRightSide {
    public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int max;
        
        for (int i = 0; i < n; i++) {
            max = 0;
            
            if(i == n - 1) {
                arr[i] = -1;
                break;
            }
            
            for(int j = n - 1; j > i; j--) {
               if (arr[j] > max) {
                   max = arr[j];
               }
            }
            
            arr[i] = max;
        }
        
        return arr;
    }
}
