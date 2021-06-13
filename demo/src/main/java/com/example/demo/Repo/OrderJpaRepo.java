package com.example.demo.Repo;

import com.example.demo.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepo extends JpaRepository<Order,String> {
}
