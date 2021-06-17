package com.spring.demo.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.demo.dao.UserRepository;
import com.spring.demo.entity.User;

@Service
public class UserDetailsServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;


	@Override
	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
	}

	@Override
	public void remove(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> userOptional = userRepository.findByEmail(email);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(email);
        }
        return UserDetailsImpl.build(userOptional.get());

	}

}