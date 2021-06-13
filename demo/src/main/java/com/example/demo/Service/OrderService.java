package com.example.demo.Service;

import com.example.demo.Exception.BuciExeption;
import com.example.demo.Model.Food;
import com.example.demo.Model.Order;
import com.example.demo.Model.User;
import com.example.demo.Repo.FoodJpaRepo;
import com.example.demo.Repo.OrderJpaRepo;
import com.example.demo.Repo.UserJpaRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    public final FoodJpaRepo foodJpaRepo;
    public final OrderJpaRepo orderJpaRepo;
    public final UserJpaRepo userJpaRepo;

    public void create(Order order) {

        if (order.getOrderedBy().getId() == null) {
            validateOrder(order);
            var user2 = User.builder()
                    .email(order.getOrderedBy().getEmail())
                    .fullAddress(order.getOrderedBy().getFullAddress())
                    .build();

            userJpaRepo.save(user2);
            order.setOrderedBy(user2);
        } else {
            String userid = order.getOrderedBy().getId();
            User user1 = userJpaRepo.findById(userid).orElse(null);
            if (user1 == null) {
                throw new BuciExeption("Hiba, mivel a user null");
            } else {
                if (!user1.getFullAddress().equals(order.getOrderedBy().getFullAddress())) {
                    throw new BuciExeption("Nem stimmel a cím");
                }
                if (!user1.getEmail().equals(order.getOrderedBy().getEmail())) {
                    throw new BuciExeption("Nem stimmel az email");
                }
            }
            order.setOrderedBy(user1);
        }


        List<Food> foodList = new ArrayList<>();
        for (int i = 0; i < order.getFoods().size(); i++) {
            Food food = foodJpaRepo.findById(order.getFoods().get(i).getId()).orElse(null);
            if(food == null){
                throw new BuciExeption("Nincs kaja");
            } else {
                foodList.add(food);
            }
        }
        order.setFoods(foodList);
        orderJpaRepo.save(order);

    }

    private void validateOrder(Order order) {
//        if (!StringUtils.hasText(order.getId())) {
//            throw new BuciExeption("Nincs ilyen id");
//        }
        if (!StringUtils.hasText(order.getOrderedBy().getFullAddress())) {
            throw new BuciExeption("Cím megadása kötelező");
        }
        if (!StringUtils.hasText(order.getOrderedBy().getEmail())) {
            throw new BuciExeption("E-mail megadása kötelező megadása kötelező");
        }
    }

    public List<Order> findAll() {
        return orderJpaRepo.findAll();
    }

    public Optional<Order> findById(String id) {
        return orderJpaRepo.findById(id);
    }

}
