package com.klef.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.demo.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product,String> {
	List<Product> findByPname(String product);
	Product findByPid(int pid);

}
