package com.codeme.pro.interview.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyCacheImpl<K, V> implements MyCache<K, V> {

  private final int capacity;
  private final LinkedList<K> lru;
  private final Map<K, V> map;

  public MyCacheImpl(int capacity) {
    this.capacity = capacity;
    this.lru = new LinkedList<>();
    this.map = new HashMap<>();
  }

  @Override
  public synchronized V get(K key) {
    if (!map.containsKey(key)) {
      return null;
    }

    lru.remove(key);
    lru.addFirst(key);
    return map.get(key);
  }

  @Override
  public synchronized boolean put(K key, V value) {
    if (map.containsKey(key)) {
      //Move to Head
      lru.remove(key);
      lru.addFirst(key);

      //Update the new value
      map.put(key, value);
      return true;
    }

    V oldValue = map.get(key);
    if (map.size() == capacity) {
      // Remove the long-lived object from LRU as well as from map
      map.remove(lru.removeLast());
    }

    // Add the new object to the cache, and add first in the LRU
    map.put(key, value);
    lru.addFirst(key);

    return value.equals(oldValue);
  }

  @Override
  public Map<K, V> getCache() {
    return this.map;
  }

  public List<K> getKeysInOrder() {
    return new LinkedList<>(lru);
  }
}
