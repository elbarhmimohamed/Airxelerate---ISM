package com.ism.mapper;

import com.ism.dto.RoleDto;
import com.ism.dto.UserDto;
import com.ism.model.Role;
import com.ism.model.User;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDto convertToDto(Role role) {

        RoleDto roleDto = new RoleDto();

        roleDto.setId(role.getId());
        roleDto.setName(role.getName());

        return roleDto;
    }

    public Role convertToEntity(RoleDto roleDto){

        Role role = new Role();

        role.setId(roleDto.getId());
        role.setName(roleDto.getName());

        return role;
    }
}
