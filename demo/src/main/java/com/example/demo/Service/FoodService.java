package com.example.demo.Service;

import com.example.demo.Exception.BuciExeption;
import com.example.demo.Model.Food;
import com.example.demo.Model.Order;
import com.example.demo.Repo.FoodJpaRepo;
import com.example.demo.Repo.OrderJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FoodService {
    public final FoodJpaRepo foodJpaRepo;
    public final OrderJpaRepo orderJpaRepo;

    public void create(Food food) {
        validateFood(food);
        foodJpaRepo.save(food);
    }

    private void validateFood(Food food) throws BuciExeption {
        if (!StringUtils.hasText(food.getName())) {
            throw new BuciExeption("Nincs ilyen nevű kaja");
        }
        if (!StringUtils.hasText(food.getDescription())) {
            throw new BuciExeption("Nincs leírása a kajának");
        }
        if (!StringUtils.hasText(food.getPrice().toString()) && food.getPrice()> 0 ) {
            throw new BuciExeption("Nincs beárazva");
        }
//        if (!StringUtils.hasText(food.getId())) {
//            throw new BuciExeption("Nincs ilyen id");
//        }
    }

    public Food update(Food food, String id) {
        validateFood(food);
        return foodJpaRepo.save(food.toBuilder().id(id).build());
    }

    public List<Food> findAll() {
        return foodJpaRepo.findAll();
    }

    public Optional<Food> findById(String id) {
        return foodJpaRepo.findById(id);
    }

}
