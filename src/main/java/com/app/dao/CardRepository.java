package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
