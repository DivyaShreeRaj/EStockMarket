package com.market.stock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.market.stock.domain.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

}
