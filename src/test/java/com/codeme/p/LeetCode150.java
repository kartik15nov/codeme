package com.codeme.p;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class LeetCode150 {

  @Test
  public void test_80_remove_duplicates_from_sorted_array() {
    int[] nums = new int[]{1, 2, 2};
    int write = 0;                      // next position to write
    for (int n : nums) {                // read every element
      if (write < 2 || n != nums[write - 2]) {
        nums[write++] = n;          // keep it
      }
    }
    System.out.println(write);
  }

  @Test
  public void removeElement() {

    int[] nums = {0,1,2,2,3,0,4,2};
    int val = 2;

    int k = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != val) {
        nums[k++] = nums[i];
      }
    }
    System.out.println(k +" "+ Arrays.toString(nums));
  }
}