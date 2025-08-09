package com.pharmacy.service;

import com.pharmacy.dto.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PharmacyService {

  // Later: inject repositories and map entities -> DTOs

  public List<DashboardStatDto> dashboardStats() {
    return List.of(
      new DashboardStatDto(1, "Total Medicines", 123),
      new DashboardStatDto(2, "Pending Orders", 15),
      new DashboardStatDto(3, "Delivered Orders", 98)
    );
  }

  public List<MedicineDto> medicines() {
    // repo.findAll().stream().map(this::toDto).toList()
    return List.of(
      new MedicineDto(1L, "Paracetamol", "500mg", new BigDecimal("3.99"), true),
      new MedicineDto(2L, "Ibuprofen", "200mg", new BigDecimal("4.99"), false),
      new MedicineDto(3L, "Amoxicillin", "250mg", new BigDecimal("12.50"), true)
    );
  }

  public List<OrderDto> orders() {
    return List.of(
      new OrderDto("ORD001", "John Doe", new BigDecimal("58.99"), "Shipped"),
      new OrderDto("ORD002", "Jane Smith", new BigDecimal("23.49"), "Processing"),
      new OrderDto("ORD003", "Bob Johnson", new BigDecimal("134.00"), "Delivered")
    );
  }
}