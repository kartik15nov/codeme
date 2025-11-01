package com.codeme.pro.interview.lru;

import java.util.Collections;
import java.util.Map;

public interface MyCache<K, V> {

  V get(K key);

  boolean put(K key, V value);

  default Map<K, V> getCache() {
    return Collections.emptyMap();
  }
}
