package com.klef.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.demo.Manager;
import com.klef.demo.User;

@Repository
public interface ManagerRepo extends JpaRepository<Manager,String> {
	Manager findByUsername(String username);

}
