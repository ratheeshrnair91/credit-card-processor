package com.domain.creditcardprocessor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardInfoEntity {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true, precision = 20)
    private BigInteger cardNumber;

    @Column(nullable = false)
    private BigInteger maxLimit;
}
