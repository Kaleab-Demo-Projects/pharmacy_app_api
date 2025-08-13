package com.pharmacy.service;

import com.pharmacy.dto.*;
import com.pharmacy.firestore.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PharmacyService {

  private final MedicineStore medicineStore;
  private final OrderStore orderStore;

  public PharmacyService(MedicineStore medicineStore, OrderStore orderStore) {
    this.medicineStore = medicineStore;
    this.orderStore = orderStore;
  }

  public List<DashboardStatDto> dashboardStats() {
    var meds = medicineStore.findAll();
    var orders = orderStore.findAll();

    int totalMeds = meds.size();
    int pending = (int) orders.stream()
        .filter(o -> "Pending".equalsIgnoreCase(o.getStatus())
                  || "Processing".equalsIgnoreCase(o.getStatus()))
        .count();
    int delivered = (int) orders.stream()
        .filter(o -> "Delivered".equalsIgnoreCase(o.getStatus()))
        .count();

    return List.of(
      new DashboardStatDto(1, "Total Medicines", totalMeds),
      new DashboardStatDto(2, "Pending Orders", pending),
      new DashboardStatDto(3, "Delivered Orders", delivered)
    );
  }

  public List<MedicineDto> medicines() { return medicineStore.findAll(); }
  public List<OrderDto> orders() { return orderStore.findAll(); }

  // (Optional) add passthrough create/update methods later:
  // public void upsertMedicine(MedicineDto m) { medicineStore.upsert(m); }
  // public void upsertOrder(OrderDto o) { orderStore.upsert(o); }
}