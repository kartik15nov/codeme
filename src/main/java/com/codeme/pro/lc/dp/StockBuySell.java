package com.codeme.pro.lc.dp;

import static java.lang.Math.max;

public class StockBuySell {

  public static void main(String[] args) {

  }

  public static int buySell_brute_force(int[] price) {
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

  public static int buySell_dp(int[] prices) {
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
