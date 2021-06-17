package com.spring.demo.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.demo.dao.RoleRepository;
import com.spring.demo.entity.ERole;
import com.spring.demo.entity.Role;
@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> findById(int id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void remove(int id) {
        roleRepository.deleteById(id);
    }

	@Override
    public Optional<Role> findByName(ERole name) {
        return roleRepository.findByName(name);
    }
}