package com.domain.creditcardprocessor.repository;

import com.domain.creditcardprocessor.entity.CardInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditInfoRepository extends JpaRepository<CardInfoEntity, Integer> {}
