package com.codeme.pro.study.dp;

public class RodCutting {

  public static void main(String[] args) {
    System.out.println(rodCutting(new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 4));
    System.out.println(rodCutting(new int[]{10, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 4));
  }

  public static int rodCutting(int[] price, int n) {
    int[] optimalSolutions = new int[n + 1];
    int[] cutAt = new int[n + 1];

    // No revenue for 0 cut
    optimalSolutions[0] = 0;

    for (int i = 1; i <= n; i++) {
      int maxRev = Integer.MIN_VALUE;
      int bestCut = 0;

      for (int j = 1; j <= i; j++) {
        int revenue = price[j - 1] + optimalSolutions[i - j];

        System.out.printf("i: %s, j: %s, rev: %s, %s-Price: %s, %s-optimal: %s%n",
            i, j, revenue, j, price[j - 1], i - j, optimalSolutions[i - j]);

        if (revenue > maxRev) {
          maxRev = revenue;
          bestCut = j;
        }
      }

      optimalSolutions[i] = maxRev;
      cutAt[i] = bestCut;
    }

    // Print the final cuts
    System.out.print("Cuts: ");
    int len = n;
    while (len > 0) {
      System.out.print(cutAt[len] + " ");
      len = len - cutAt[len];
    }

    System.out.println();
    return optimalSolutions[n];
  }
}
