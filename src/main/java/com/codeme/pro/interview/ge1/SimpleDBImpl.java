package com.codeme.pro.interview.ge1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class SimpleDBImpl implements SimpleDB {

  private final Map<String, Integer> db = new HashMap<>();
  private final Deque<Map<String, Integer>> transactionQueue = new ArrayDeque<>();


  @Override
  public void Set(String key, Integer value) {
    if (!transactionQueue.isEmpty()) {
      transactionQueue.peek().put(key, db.get(key));
    }
    db.put(key, value);
  }

  @Override
  public Integer Get(String key) {
    return db.get(key);
  }

  @Override
  public void Unset(String key) {
    if (!transactionQueue.isEmpty()) {
      transactionQueue.peek().put( key, db.get(key));
    }
    db.remove(key);
  }

  @Override
  public void Begin() {
    transactionQueue.push(new HashMap<>());
  }

  @Override
  public void Commit() throws Exception {
    if (transactionQueue.isEmpty()) {
      throw new RuntimeException("No transaction to commit");
    }
    transactionQueue.clear();
  }

  @Override
  public void Rollback() throws Exception {
    if (transactionQueue.isEmpty()) {
      throw new RuntimeException("No transaction to rollback");
    }

    for (Map.Entry<String, Integer> entry : transactionQueue.pop().entrySet()) {
      if (entry.getValue() == null) {
        db.remove(entry.getKey());
      } else {
        db.put(entry.getKey(), entry.getValue());
      }
    }
  }
}
