package com.ojt_server.modules.address.repository;

import com.ojt_server.modules.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
