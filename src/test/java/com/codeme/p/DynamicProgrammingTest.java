package com.codeme.p;

import static java.lang.Math.max;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DynamicProgrammingTest {

  @Test
  public void rodCutting() {
    System.out.println(rodCutting(new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 4));
    System.out.println(rodCutting(new int[]{10, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 4));
  }

  private int rodCutting(int[] price, int n) {
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

  @Test
  public void stockBuySell() {
    assertEquals(5, buySell_brute_force(new int[]{7, 1, 5, 3, 6, 4}));
    assertEquals(0, buySell_brute_force(new int[]{7, 6, 4, 3, 1}));

    assertEquals(5, buySell_dp(new int[]{7, 1, 5, 3, 6, 4}));
    assertEquals(0, buySell_dp(new int[]{7, 6, 4, 3, 1}));
  }

  private int buySell_brute_force(int[] price) {
    int max_profit = Integer.MIN_VALUE;

    // Check for each price, what is the max profit with other price in the future.
    // At the end the max_profit will be returned
    for (int i = 0; i < price.length - 1; i++) {
      for (int j = i; j < price.length; j++) {
        int profit = price[j] - price[i];
        if (profit > max_profit) {
          max_profit = profit;
        }
      }
    }

    return max(max_profit, 0);
  }

  private int buySell_dp(int[] prices) {
    int min_price = prices[0];
    int max_profit = 0;

    // Here in DP we are maintaining the state of minimum price everytime we traverse through new price.
    // So that makes loop move forward only and get the solution in O(n)

    //This question is simple, cz the ask is to find the best buy->sell/max profit considering one transaction. i.e. one time buy, one time sell.
    for (int j : prices) {
      if (j < min_price) {
        min_price = j;
      } else {
        int profit = j - min_price;
        if (profit > max_profit) {
          max_profit = profit;
        }
      }
    }
    return max_profit;
  }
}