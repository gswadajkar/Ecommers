package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.TechnicalDetails;

public interface TechnicalDetailsRepository extends JpaRepository<TechnicalDetails, Integer> {

}
