package com.spring.demo.service;

import java.util.Optional;

import com.spring.demo.entity.ERole;
import com.spring.demo.entity.Role;

public interface IRoleService extends GeneralService<Role> {
	Optional<Role> findByName(ERole name);
}