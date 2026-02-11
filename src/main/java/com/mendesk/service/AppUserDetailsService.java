package com.mendesk.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mendesk.entity.User;
import com.mendesk.entity.UserRole;
import com.mendesk.repository.UserRepo;

@Service("userDetailsService")
public class AppUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());

		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				user.isEnabled(),
				true,
				true,
				true,
				authorities
		);
	}

	private List<GrantedAuthority> buildUserAuthority(UserRole role) {
		Set<GrantedAuthority> setAuths = new HashSet<>();
		setAuths.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
		return new ArrayList<>(setAuths);
	}
}
