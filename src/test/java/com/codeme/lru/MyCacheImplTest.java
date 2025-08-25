package com.codeme.lru;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyCacheImplTest {

  private MyCacheImpl<Integer, String> cache;

  @BeforeEach
  void setUp() {
    cache = new MyCacheImpl<>(3); // capacity = 3
  }

  @Test
  void testBasicPutAndGetOrder() {
    cache.put(1, "A");
    cache.put(2, "B");
    cache.put(3, "C");

    assertEquals("[1, 2, 3]", cache.getKeysInOrder().toString());
    assertEquals("A", cache.get(1));
    // Accessing 1 moves it to most recent
    assertEquals("[2, 3, 1]", cache.getKeysInOrder().toString());
  }

  @Test
  void testUpdateExistingValueChangesOrder() {
    cache.put(1, "A");
    cache.put(2, "B");
    cache.put(3, "C");

    assertEquals("[1, 2, 3]", cache.getKeysInOrder().toString());

    cache.put(2, "B_updated");
    assertEquals("[1, 3, 2]", cache.getKeysInOrder().toString());
  }

  @Test
  void testPutSameValueDoesNotEvictButMovesToRecent() {
    cache.put(1, "A");
    cache.put(2, "B");
    cache.put(3, "C");

    assertEquals("[1, 2, 3]", cache.getKeysInOrder().toString());

    boolean result = cache.put(2, "B"); // same value
    assertFalse(result);
    assertEquals("[1, 3, 2]", cache.getKeysInOrder().toString());
  }

  @Test
  void testEvictionWhenCapacityExceeded() {
    cache.put(1, "A");
    cache.put(2, "B");
    cache.put(3, "C");
    assertEquals("[1, 2, 3]", cache.getKeysInOrder().toString());

    cache.put(4, "D"); // evict 1
    assertEquals("[2, 3, 4]", cache.getKeysInOrder().toString());

    cache.put(5, "E"); // evict 2
    assertEquals("[3, 4, 5]", cache.getKeysInOrder().toString());
  }

  @Test
  void testAccessAffectsEvictionOrder() {
    cache.put(1, "A");
    cache.put(2, "B");
    cache.put(3, "C");
    assertEquals("[1, 2, 3]", cache.getKeysInOrder().toString());

    cache.get(1); // now 1 is most recent
    assertEquals("[2, 3, 1]", cache.getKeysInOrder().toString());

    cache.put(4, "D"); // evict 2
    assertEquals("[3, 1, 4]", cache.getKeysInOrder().toString());
  }
}
