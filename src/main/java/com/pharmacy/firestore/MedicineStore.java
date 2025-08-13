package com.pharmacy.firestore;

import com.pharmacy.dto.MedicineDto;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Repository
public class MedicineStore {
  private static final String COL = "medicines";
  private final Firestore db;

  public MedicineStore(Firestore db) { this.db = db; }

  public List<MedicineDto> findAll() {
    try {
      ApiFuture<QuerySnapshot> fut = db.collection(COL).get();
      List<QueryDocumentSnapshot> docs = fut.get().getDocuments();
      List<MedicineDto> out = new ArrayList<>();
      for (var d : docs) {
        out.add(new MedicineDto(
            d.getLong("id"),                       // stored as number
            d.getString("name"),
            d.getString("dosage"),
            d.getDouble("price") == null ? null : java.math.BigDecimal.valueOf(d.getDouble("price")),
            Boolean.TRUE.equals(d.getBoolean("inStock"))
        ));
      }
      return out;
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Failed to load medicines", e);
    }
  }

  public void upsert(MedicineDto m) {
    try {
      db.collection(COL).document(String.valueOf(m.getId())).set(
        java.util.Map.of(
          "id", m.getId(),
          "name", m.getName(),
          "dosage", m.getDosage(),
          "price", m.getPrice() == null ? null : m.getPrice().doubleValue(),
          "inStock", m.isInStock()
        )
      ).get();
    } catch (InterruptedException | ExecutionException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException("Failed to upsert medicine " + m.getId(), e);
    }
  }
}