package com.faos.fuelagency.entity;
import jakarta.persistence.*;
@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String contactNumber;
    private String address;
    private String status;

    public Supplier() {}

    public Supplier(String name, String contactNumber,String address,String status) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.status=status;
        this.address=address;
       
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status=status;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address=address;
    }


    
}
