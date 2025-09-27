package com.codeme.p;

import java.util.ArrayList;
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
      if (map.containsKey(j)) {
        int count = map.get(j) + 1;
        map.put(j, count);
      } else {
        map.put(j, 1);
      }
    }

    List<Integer> res = new ArrayList<>();
    List<Integer> values = map.entrySet().stream()
        .sorted((o1, o2) -> -o1.getValue().compareTo(o2.getValue()))
        .map(Entry::getKey)
        .toList();
    for (int i = 0; i < k; i++) {
      res.add(values.get(i));
    }
    return res;
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
