package com.osama.ecommerceapplication.addresses;

import com.osama.ecommerceapplication.common.BaseEntity;
import com.osama.ecommerceapplication.users.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@Setter
@Table(name = "addresses")
@Entity
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    private String buildingName;

    @ManyToMany(mappedBy = "addresses")
    private Set<User> users;
}
