/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_algorithm.arrays;

import com.google.gson.Gson;

/**
 *
 * @author venkateshwarans
 */
// https://leetcode.com/problems/product-of-array-except-self/submissions/


// [2, 3, 4, 5]

public class ProductOfArraysExceptSelf {
  static boolean show = true;
  
  
  // to every node accumulate of product of values upto its previous index from left to right.
  // do the same from right to left to get the result.
  
  // Time: O(n) space: O(n) but result space is not counted as per leetcode.
  public static int[] productOfArrayExceptSelf(int[] arr){
    
    int res[] = new int[arr.length];
    if(arr.length==0)
      return res;
    int prod = 1;
    res[0] = 1;
    for(int i=1;i<arr.length;i++){
      res[i] = prod*arr[i-1];
      prod *= arr[i-1]; 
    }
    prod = 1;
    for(int i=arr.length-2;i>=0;i--){
      res[i] *= prod*arr[i+1];
      prod *= arr[i+1]; 
    }
    return res;
  }
  
  // calculate overall product and then divide with self.
  // Time: O(n) space: O(n) but result space is not counted as per leetcode.
  public int[] productOfArrayExceptSelfAlt(int[] arr) {
      int res[] = new int[arr.length];
      if(arr.length==0)
        return res;
      res[0] = 1;
      for(int i=1;i<arr.length;i++){
        res[i] = res[i-1]*arr[i-1];
      }
      int prod = 1;
      for(int i=arr.length-2;i>=0;i--){
        res[i] *= prod*arr[i+1];
        prod *= arr[i+1]; 
      }
      return res;
  }
  
  
  // this problem has 2 edge cases
  // 1. with 1 zero which can be solved with prodWithoutZero.
  // 2. two more zeroes - in this case output is always a zero array.
  // Time: O(n) space: O(1) using division.
  public int[] productOfArrayExceptSelfDivision(int[] arr) {
    int prod = 1;
        int prodWithoutZero = 1;
        int zeroCount = 0;
        boolean allZero = true;
        for(int i=0;i<arr.length;i++){
            prod*=arr[i];
            if(arr[i]!=0) {
                prodWithoutZero*=arr[i];
                allZero = false;
            }else{
                zeroCount++;
            }
        }
        if(zeroCount>1) {
            prodWithoutZero = 0;
            prod = 0;
        }
        if(allZero) prodWithoutZero = 0;
        for(int i=0;i<arr.length;i++){
            arr[i] = arr[i]==0 ? prodWithoutZero : prod/arr[i];
        }
        return arr;
  }
  
  public static void main(String[] args){
    test(productOfArrayExceptSelf(new int[]{2, 3, 4, 5}), new int[]{2,2,6,24});
    test(productOfArrayExceptSelf(new int[]{}), new int[]{});
    test(productOfArrayExceptSelf(new int[]{1}), new int[]{1});
    test(productOfArrayExceptSelf(new int[]{1,2}), new int[]{1,1});
  }
  
  public static void test(int[] got, int exp[]){
    Gson gson = new Gson();
    String gotStr = gson.toJson(got);
    String expStr = gson.toJson(exp);
    System.out.println(gotStr.equals(expStr));
    if(show || !gotStr.equals(expStr)){
      System.out.println("got     : "+gson.toJson(gotStr));
      System.out.println("expected: "+gson.toJson(expStr));
    }
  }
}
