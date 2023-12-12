package com.ism.service;


import com.ism.dto.RoleDto;
import com.ism.mapper.RoleMapper;
import com.ism.model.Role;
import com.ism.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;

    public List<RoleDto> getRoles(){

        List<Role> roles = roleRepository.findAll();
        List<RoleDto> roleDtos = roles.stream().map( r -> roleMapper.convertToDto(r))
                .collect(Collectors.toList());

        return roleDtos;
    }


    public RoleDto getRoleById(Long id){
        Optional<Role> role = roleRepository.findById(id);
        if(role.isPresent()){
            return roleMapper.convertToDto(role.get());
        }
        return  null;
    }

    public RoleDto getRoleByName(String name){
        Optional<Role> role = roleRepository.findByName(name);
        if(role.isPresent()){
            return roleMapper.convertToDto(role.get());
        }
        return  null;
    }

    public Role addRole(RoleDto roleDto){
        Role role = roleMapper.convertToEntity(roleDto);
        return roleRepository.save(role);
    }

    public void deleteRole(Long id){
        roleRepository.deleteById(id);
    }
}
