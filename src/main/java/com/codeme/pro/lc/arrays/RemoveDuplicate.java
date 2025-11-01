package com.codeme.pro.lc.arrays;

import java.util.Arrays;

public class RemoveDuplicate {

  public static void main(String[] args) {
    int[] nums = new int[]{1, 2, 2};
    System.out.println(Arrays.toString(test_80_remove_duplicates_from_sorted_array(nums)));
  }

  public static int[] test_80_remove_duplicates_from_sorted_array(int[] nums) {
    int write = 0;                      // next position to write
    for (int n : nums) {                // read every element
      if (write < 2 || n != nums[write - 2]) {
        nums[write++] = n;          // keep it
      }
    }
    System.out.println(write);
    return nums;
  }
}
