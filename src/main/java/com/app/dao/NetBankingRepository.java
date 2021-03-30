package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.NetBanking;

public interface NetBankingRepository extends JpaRepository<NetBanking, Long>{

}
