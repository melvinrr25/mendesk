package com.mendesk.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.mendesk.entity.User;

@Repository
public class UserDao {

	@PersistenceContext
	EntityManager manager;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {
		
		String sql = "SELECT u FROM User u WHERE u.username = :username";
				
		List<User> users = manager.createQuery(sql).setParameter("username", username).getResultList();
		
		return users.size() > 0 ? users.get(0) : null;

	}

}
