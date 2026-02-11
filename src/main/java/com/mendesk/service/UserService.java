package com.mendesk.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mendesk.entity.User;
import com.mendesk.entity.UserRole;
import com.mendesk.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	public User registerUser(String username, String email, String password) {
		if (userRepo.existsByUsername(username)) {
			return null;
		}
		if (userRepo.existsByEmail(email)) {
			return null;
		}

		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(UserRole.END_USER);
		user.setEnabled(true);

		return userRepo.save(user);
	}

	@Transactional
	public User createAgent(String username, String email, String password) {
		if (userRepo.existsByUsername(username)) {
			return null;
		}
		if (userRepo.existsByEmail(email)) {
			return null;
		}

		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(UserRole.AGENT);
		user.setEnabled(true);

		return userRepo.save(user);
	}

	@Transactional
	public User createAdmin(String username, String email, String password) {
		if (userRepo.existsByUsername(username)) {
			return null;
		}
		if (userRepo.existsByEmail(email)) {
			return null;
		}

		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(UserRole.ADMIN);
		user.setEnabled(true);

		return userRepo.save(user);
	}

	public User findByUsername(String username) {
		return userRepo.findByUsername(username).orElse(null);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email).orElse(null);
	}

	public User findById(Integer id) {
		return userRepo.findById(id).orElse(null);
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public List<User> findAllAgents() {
		return findAll().stream()
				.filter(u -> u.getRole() == UserRole.AGENT || u.getRole() == UserRole.ADMIN)
				.toList();
	}

	@Transactional
	public User updateUser(User user) {
		return userRepo.save(user);
	}

	@Transactional
	public void deleteUser(Integer id) {
		userRepo.deleteById(id);
	}

	public boolean usernameExists(String username) {
		return userRepo.existsByUsername(username);
	}

	public boolean emailExists(String email) {
		return userRepo.existsByEmail(email);
	}

	public boolean isAdmin(User user) {
		return user != null && user.getRole() == UserRole.ADMIN;
	}

	public boolean isAgent(User user) {
		return user != null && (user.getRole() == UserRole.AGENT || user.getRole() == UserRole.ADMIN);
	}
}
