package com.mendesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mendesk.entity.Request;

public interface RequestRepo extends JpaRepository<Request, Integer> {

}
