//Given a fixed-length integer array arr, duplicate each occurrence of zero, shifting the remaining elements to the right.
//Note that elements beyond the length of the original array are not written. Do the above modifications to the input array in place and do not return anything.
//https://leetcode.com/explore/learn/card/fun-with-arrays/525/inserting-items-into-an-array/3245/

package arrays101;

public class DuplicateZeros {
    public void duplicateZeros(int[] arr) {
        //get 0s
        //shift n-times
        for(int i=0; i < arr.length; i++) {
            if(arr[i] == 0 && i != arr.length - 1) {
                for(int j=arr.length - 2; j > i; j--) {
                    arr[j + 1] = arr[j];
                }
                arr[i + 1] = 0;
                i++;
            }
        }
    }
}
