package com.pharmacy.firestore;

import com.pharmacy.dto.OrderDto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class OrderStore {
  private static final String COL = "orders";
  private final Firestore db;

  public OrderStore(Firestore db) { this.db = db; }

  public List<OrderDto> findAll() {
    try {
      ApiFuture<QuerySnapshot> fut = db.collection(COL).get();
      List<QueryDocumentSnapshot> docs = fut.get().getDocuments();
      List<OrderDto> out = new ArrayList<>();
      for (var d : docs) {
        out.add(new OrderDto(
          d.getString("id"),
          d.getString("customer"),
          d.getDouble("total") == null ? null : java.math.BigDecimal.valueOf(d.getDouble("total")),
          d.getString("status")
        ));
      }
      return out;
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Failed to load orders", e);
    }
  }

  public void upsert(OrderDto o) {
    try {
      db.collection(COL).document(o.getId()).set(
        java.util.Map.of(
          "id", o.getId(),
          "customer", o.getCustomer(),
          "total", o.getTotal() == null ? null : o.getTotal().doubleValue(),
          "status", o.getStatus()
        )
      ).get();
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Failed to upsert order " + o.getId(), e);
    }
  }
}