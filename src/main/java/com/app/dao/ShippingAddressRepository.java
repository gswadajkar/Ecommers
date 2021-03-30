package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.ShippingAddress;

public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, Integer>{

}
