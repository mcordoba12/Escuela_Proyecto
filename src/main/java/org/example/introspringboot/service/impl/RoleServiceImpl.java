package org.example.introspringboot.service.impl;

import org.example.introspringboot.entity.Role;
import org.example.introspringboot.repository.RoleRepository;
import org.example.introspringboot.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
