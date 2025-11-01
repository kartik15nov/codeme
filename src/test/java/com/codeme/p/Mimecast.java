package com.codeme.p;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.junit.jupiter.api.Test;

public class Mimecast {

  @Test
  public void test() {
    System.out.println("Hello");

    int[] arr = new int[]{-1, 0, 2, 2, 2, 2, 3, 3, 3, 1, 1, 2};
    System.out.println(getTopK(arr, 2));

  }

  public List<Integer> getTopK(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int j : arr) {
      map.put(j, map.getOrDefault(j, 0) + 1);
    }

    return map.entrySet().stream()
        .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
        .map(Entry::getKey)
        .limit(k)
        .toList();
  }

  int[] getTopK1(int[] arr, int k) {

    int max_count = Integer.MIN_VALUE;
    int lastPosition = 0;
    //int[] arr = new int[]{-1, 0, 2,2,2,2,3, 3, 3,3,3,, 1, 1, 2};

    int[] res = new int[k];
    for (int i = 0; i < arr.length; i++) {
      int j = 0;
      int currCount = 0;
      if (arr[i] != arr[j]) {
        currCount = 1;
        j = i;
      } else {
        currCount++;
        if (currCount > max_count) {
          max_count = currCount;

        }

      }
    }
    return res;
  }
}
