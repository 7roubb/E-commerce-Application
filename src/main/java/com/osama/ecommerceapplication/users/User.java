package com.osama.ecommerceapplication.users;

import com.osama.ecommerceapplication.addresses.Address;
import com.osama.ecommerceapplication.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@Validated
@ToString
@Entity
@Table(
        name = "\"user\"",
        indexes = {
                @Index(name = "idx_user_userName", columnList = "username"),
                @Index(name = "idx_user_email", columnList = "email")
        }
)

public class User extends BaseEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false )
    private String fullName;

    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_address",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private Set<Address> addresses;
}
