package com.mendesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendesk.entity.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
