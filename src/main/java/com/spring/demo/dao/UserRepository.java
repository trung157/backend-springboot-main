package com.spring.demo.dao;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.spring.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "select * from Users where lock = 0", nativeQuery = true)
	public List<User> listUserNolock();
	@Transactional
	@Modifying
		@Query(value = "Update Users set lock=1 where uid=?1", nativeQuery = true)
	void lockUser(int uid);
	@Transactional
	@Modifying
		@Query(value = "Update Users set lock=0 where uid=?1", nativeQuery = true)
	void unlockUser(int uid);
	@Query(value = "select * from Users where email = ?1", nativeQuery = true)
	Optional<User> findByEmail(String email);
//	@Query(value = "select email from Users where email = ?1", nativeQuery = true)
//	boolean existByEmail(String email);
//	@Query(value = "select phone from Users where phone = ?1", nativeQuery = true)
//	boolean existByPhone(String phone);
}
