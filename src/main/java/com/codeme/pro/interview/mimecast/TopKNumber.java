package com.codeme.pro.interview.mimecast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class TopKNumber {

  public static void main(String[] args) {
    System.out.println("Hello");

    int[] arr = new int[]{-1, 0, 2, 2, 2, 2, 3, 3, 3, 1, 1, 2};
    System.out.println(Arrays.toString(getTopK(arr, 2)));
  }

  public static int[] getTopK(int[] arr, int k) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int j : arr) {
      map.put(j, map.getOrDefault(j, 0) + 1);
    }

    return map.entrySet().stream()
        .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
        .map(Entry::getKey)
        .limit(k)
        .mapToInt(Integer::intValue)
        .toArray();
  }

  public static ArrayList<Integer> getTopKPriorityQueue(int[] nums, int k) {

    PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    for (int num : nums) {
      minHeap.offer(num);

      // Keep only K elements in the heap
      if (minHeap.size() > k) {
        minHeap.poll(); // removes smallest
      }
    }

    // Heap now has K largest elements
    return new ArrayList<>(minHeap);
  }
}
