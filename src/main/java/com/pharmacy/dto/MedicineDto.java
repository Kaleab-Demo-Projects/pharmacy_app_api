package com.pharmacy.dto;

import java.math.BigDecimal;

public class MedicineDto {
  private Long id;
  private String name;
  private String dosage;
  private BigDecimal price;
  private boolean inStock;

  public MedicineDto() {}
  public MedicineDto(Long id, String name, String dosage, BigDecimal price, boolean inStock) {
    this.id = id; this.name = name; this.dosage = dosage; this.price = price; this.inStock = inStock;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getDosage() { return dosage; }
  public void setDosage(String dosage) { this.dosage = dosage; }
  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }
  public boolean isInStock() { return inStock; }
  public void setInStock(boolean inStock) { this.inStock = inStock; }
}