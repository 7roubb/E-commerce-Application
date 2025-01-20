package com.osama.ecommerceapplication.addresses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    int deleteAddressByAddressIdAndUsers_Id(Long addressId, Long usersId);
    Optional<List<Address>> findAllByUsers_Id(Long usersId);
    Optional<Address> getAddressByAddressId(Long addressId);
}
