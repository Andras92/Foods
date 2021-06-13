package com.example.demo.Repo;

import com.example.demo.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodJpaRepo extends JpaRepository<Food,String> {
}
