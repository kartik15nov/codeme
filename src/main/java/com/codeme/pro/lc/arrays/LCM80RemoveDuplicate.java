package com.codeme.pro.lc.arrays;

import java.util.Arrays;

/**
 * LC Medium 80
 */
public class LCM80RemoveDuplicate {

  public static void main(String[] args) {
    int[] nums = new int[]{1, 2, 2};
    int[] nums1 = new int[]{1, 2, 2, 2, 4, 5};

    System.out.println(Arrays.toString(test_80_remove_duplicates_from_sorted_array(nums)));
    System.out.println(Arrays.toString(test_80_remove_duplicates_from_sorted_array(nums1)));

    System.out.println(
        Arrays.toString(test_80_remove_duplicates_from_sorted_array_brute_force(nums)));
    System.out.println(
        Arrays.toString(test_80_remove_duplicates_from_sorted_array_brute_force(nums1)));

  }

  /**
   * |1|2|2|4|5|
   *
   * <p>
   * for each n, nums[write++] = n -> if no of occurrence for n is < 2.
   * <p>
   * basically we are writing to the n index number to the last write index - no of occ allowed.
   * simple.
   */
  public static int[] test_80_remove_duplicates_from_sorted_array(int[] nums) {
    int write = 0;                      // next position to write
    for (int n : nums) {                // read every element
      if (write < 2 || n != nums[write - 2]) {
        nums[write++] = n;          // keep it
      }
    }
    return nums;
  }

  /**
   * |1|2|2|4|5|
   *
   * <p>
   * for each n, nums[write++] = n -> if no of occurrence for n is < 2.
   */
  public static int[] test_80_remove_duplicates_from_sorted_array_brute_force(int[] nums) {
    int count = 1;
    int k = 0;

    for (int i = 0; i < nums.length; i++) {
      if (nums[i] != nums[k]) {
        k++;

        // Expect i should always be ahead of k
        if (i > k) {
          nums[k] = nums[i];
        }
      } else {
        // cz first time the i & k = 0, so we want i to move forward
        if (i > k) {
          count++;

          // move the k, until allowed no of occurrences, i.e 2 here.
          if (count <= 2) {
            k++;

            if (i > k) {
              nums[k] = nums[i];
            }
          }
        }
      }
    }

    return nums;
  }
}
