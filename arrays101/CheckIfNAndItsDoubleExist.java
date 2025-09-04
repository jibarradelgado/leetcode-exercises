/* Given an array arr of integers, check if there exist two indices i and j such that :

i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j] */
//https://leetcode.com/explore/learn/card/fun-with-arrays/527/searching-for-items-in-an-array/3250/

package arrays101;

public class CheckIfNAndItsDoubleExist {
    public boolean checkIfExist(int[] arr) {
        for(int i=0; i < arr.length; i++){
            for(int j=i+1; j < arr.length; j++) {
                if(arr[j] == arr[i] * 2 || (arr[i] % 2 == 0 && arr[i] / 2 == arr[j])) {
                    return true;
                }
            }
        }
        
        return false;
    }
}