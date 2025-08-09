package com.pharmacy.controller;

import com.pharmacy.dto.*;
import com.pharmacy.service.PharmacyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PharmacyController {
  private final PharmacyService svc;
  
  public PharmacyController(PharmacyService svc) { this.svc = svc; }

  @GetMapping("/dashboard/stats")
  public List<DashboardStatDto> stats() { return svc.dashboardStats(); }

  @GetMapping("/medicines")
  public List<MedicineDto> medicines() { return svc.medicines(); }

  @GetMapping("/orders")
  public List<OrderDto> orders() { return svc.orders(); }
}