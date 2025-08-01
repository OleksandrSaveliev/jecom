package com.tmdna.jecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tmdna.jecom.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
