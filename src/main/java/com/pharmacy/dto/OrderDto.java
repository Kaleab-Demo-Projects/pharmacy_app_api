package com.pharmacy.dto;

import java.math.BigDecimal;

public class OrderDto {
  private String id;
  private String customer;
  private BigDecimal total;
  private String status;

  public OrderDto() {}
  public OrderDto(String id, String customer, BigDecimal total, String status) {
    this.id = id; this.customer = customer; this.total = total; this.status = status;
  }

  public String getId() { return id; }
  public void setId(String id) { this.id = id; }
  public String getCustomer() { return customer; }
  public void setCustomer(String customer) { this.customer = customer; }
  public BigDecimal getTotal() { return total; }
  public void setTotal(BigDecimal total) { this.total = total; }
  public String getStatus() { return status; }
  public void setStatus(String status) { this.status = status; }
}