package com.codeme.lru;

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
    if (map.containsKey(key)) {
      lru.remove(key);
      lru.add(key);
      return map.get(key);
    }
    return null;
  }

  @Override
  public synchronized boolean put(K key, V value) {
    if (map.containsKey(key)) {
      V oldValue = map.get(key);

      if (oldValue != null && oldValue.equals(value)) {
        // Update the LRU
        lru.remove(key);
        lru.add(key);

        return false;
      }

      // Write to cache
      map.put(key, value);

      // Update the LRU
      lru.remove(key);
      lru.add(key);

      return true;
    } else {
      if (map.size() >= capacity) {
        // Evict the long-lived item
        K itemToEvict = lru.getFirst();
        map.remove(itemToEvict);
        lru.remove(itemToEvict);

        // write the key, value to cache
        map.put(key, value);

        // Add the new item to the lru list
        lru.add(key);
      } else {
        // write the key, value to cache
        map.put(key, value);

        // Add the new item to the lru list
        lru.remove(key);
        lru.add(key);
      }
    }
    return true;
  }

  @Override
  public Map<K, V> getCache() {
    return this.map;
  }

  public List<K> getKeysInOrder() {
    return new LinkedList<>(lru);
  }
}
