package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.VendorCategory;

public interface VendorCategoryRepository extends JpaRepository<VendorCategory, Integer> {

}
