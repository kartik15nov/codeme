package com.codeme.practice;

import org.junit.jupiter.api.Test;

class ArraysAndStringsTest {

  @Test
  public void isUnique() {
    System.out.println(isUniqueChars("Hello"));
    System.out.println(isUniqueChars("Hi"));
    System.out.println(isUniqueChars("Abeda"));
  }

  boolean isUniqueChars(String str) {
    if (str.length() > 128) {
      return false;
    }

    boolean[] char_set = new boolean[128];
    for (int i = 0; i < str.length(); i++) {
      int val = str.charAt(i);
      if (char_set[val]) {
        return false;
      }
      char_set[val] = true;
    }
    return true;
  }
}