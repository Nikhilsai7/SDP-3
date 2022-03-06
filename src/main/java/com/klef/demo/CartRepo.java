package com.klef.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.klef.demo.Cart;
import com.klef.demo.Product;
import com.klef.demo.User;

@Component
@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
Cart findById(int id);
Cart findByUserAndProduct(User u,Product p);
List<Cart> findByUser(User user);
}