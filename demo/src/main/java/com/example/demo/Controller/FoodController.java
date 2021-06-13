package com.example.demo.Controller;

import com.example.demo.Model.Food;
import com.example.demo.Service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/food")
public class FoodController {
    private final FoodService foodService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Food food) {
        foodService.create(food);
    }

    @GetMapping("")
    public List<Food> findAll() {
        return foodService.findAll();
    }

    @PutMapping("")
    public Food update(@RequestBody Food food, @RequestParam String id) {
        return foodService.update(food,id);
    }


    @GetMapping("/{id}")
    public Optional<Food> findById(@PathVariable String id) {
        return foodService.findById(id);
    }
}
