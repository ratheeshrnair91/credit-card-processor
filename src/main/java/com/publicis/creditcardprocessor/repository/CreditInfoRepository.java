package com.publicis.creditcardprocessor.repository;

import com.publicis.creditcardprocessor.entity.CardInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditInfoRepository extends JpaRepository<CardInfoEntity, Integer> {}
