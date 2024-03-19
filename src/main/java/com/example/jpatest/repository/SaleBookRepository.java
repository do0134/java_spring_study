package com.example.jpatest.repository;

import com.example.jpatest.model.SaleBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleBookRepository extends JpaRepository<SaleBooks, Long> {
    List<SaleBooks> findAll();
}
