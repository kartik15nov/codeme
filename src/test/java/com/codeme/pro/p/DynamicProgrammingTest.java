package com.codeme.pro.p;

import static com.codeme.pro.lc.dp.StockBuySell.buySell_brute_force;
import static com.codeme.pro.lc.dp.StockBuySell.buySell_dp;
import static java.lang.Math.max;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeme.pro.study.dp.RodCutting;
import org.junit.jupiter.api.Test;

class DynamicProgrammingTest {

  @Test
  public void rodCutting() {
    System.out.println(RodCutting.rodCutting(new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 4));
    System.out.println(RodCutting.rodCutting(new int[]{10, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 4));
  }


  @Test
  public void stockBuySell() {
    assertEquals(5, buySell_brute_force(new int[]{7, 1, 5, 3, 6, 4}));
    assertEquals(0, buySell_brute_force(new int[]{7, 6, 4, 3, 1}));

    assertEquals(5, buySell_dp(new int[]{7, 1, 5, 3, 6, 4}));
    assertEquals(0, buySell_dp(new int[]{7, 6, 4, 3, 1}));
  }


}