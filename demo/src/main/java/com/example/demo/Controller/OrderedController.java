package com.example.demo.Controller;

import com.example.demo.Model.Food;
import com.example.demo.Model.Order;
import com.example.demo.Repo.UserJpaRepo;
import com.example.demo.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/order")
public class OrderedController {
    private final OrderService orderService;
    private final UserJpaRepo userJpaRepo;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Order order) {
        orderService.create(order);
    }

    @GetMapping("")
    public List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Order> findById(@PathVariable String id) {
        return orderService.findById(id);
    }
}