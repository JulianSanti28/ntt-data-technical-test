package com.ntt.data.app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    private Long addressId;
    @Column(length = 60)
    private String description;
    @Column(length = 60)
    private String city;
    @OneToOne @MapsId @JoinColumn(name = "customer_id",insertable = false, updatable = false)
    private Customer customer;
}
