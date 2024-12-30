package com.faos.fuelagency.entities;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "cylinders")
public class Cylinder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id;
    private String status; // available / delivered
    private String type; // full / empty
    private float weight;
    private LocalDate lastRefillDate;
    
    @ManyToOne
    @JoinColumn(name = "supplier_id", referencedColumnName = "id") // Foreign key column
    private Supplier supplier_id;

    public Cylinder() {}

    public Cylinder(Integer id, String status, String type, float weight, LocalDate lastRefillDate,Supplier supplier_id) {
        this.id = id;
        this.status = status;
        this.type = type;
        this.weight = weight;
        this.lastRefillDate = lastRefillDate;
        this.supplier_id=supplier_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public LocalDate getLastRefillDate() {
        return lastRefillDate;
    }

    public void setLastRefillDate(LocalDate lastRefillDate) {
        this.lastRefillDate = lastRefillDate;
    }
    public Supplier getSupplier() {
        return supplier_id;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier_id = supplier;
    }
    @Override
    public String toString() {
        return "Cylinder{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", weight=" + weight +
                ", lastRefillDate=" + lastRefillDate +
                '}';
    }
}
    
