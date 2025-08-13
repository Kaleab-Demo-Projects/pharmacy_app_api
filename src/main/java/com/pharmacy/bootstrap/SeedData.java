package com.pharmacy.bootstrap;

import com.pharmacy.dto.MedicineDto;
import com.pharmacy.dto.OrderDto;
import com.pharmacy.firestore.MedicineStore;
import com.pharmacy.firestore.OrderStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Profile("seed")
public class SeedData implements CommandLineRunner {
  private final MedicineStore meds; private final OrderStore orders;
  public SeedData(MedicineStore meds, OrderStore orders) { this.meds = meds; this.orders = orders; }

  @Override public void run(String... args) {
    meds.upsert(new MedicineDto(1L,"Paracetamol","500mg", new BigDecimal("4.99"), true));
    meds.upsert(new MedicineDto(2L,"Ibuprofen","200mg", new BigDecimal("5.99"), false));
    meds.upsert(new MedicineDto(3L,"Amoxicillin","250mg", new BigDecimal("22.50"), true));

    orders.upsert(new OrderDto("ORD001","John Doe", new BigDecimal("68.99"), "Shipped"));
    orders.upsert(new OrderDto("ORD002","Jane Smith", new BigDecimal("33.49"), "Processing"));
    orders.upsert(new OrderDto("ORD003","Bob Johnson", new BigDecimal("234.00"), "Delivered"));
  }
}