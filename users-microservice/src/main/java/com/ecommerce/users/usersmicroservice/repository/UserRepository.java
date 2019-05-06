package com.ecommerce.users.usersmicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.users.usersmicroservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
