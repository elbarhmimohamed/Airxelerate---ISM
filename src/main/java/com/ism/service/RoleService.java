package com.ism.service;


import com.ism.dto.RoleDto;
import com.ism.mapper.RoleMapper;
import com.ism.model.Role;
import com.ism.model.User;
import com.ism.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id){
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            return role.get();
        }
        return  null;
    }

    public Role getRoleByName(String name){
        return roleRepository.findByName(name).orElse(null) ;
    }

    public Role addRole(RoleDto roleDto){
        Role role = roleMapper.convertToEntity(roleDto);
        return roleRepository.save(role);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }
}
