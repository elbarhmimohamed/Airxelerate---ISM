package com.ism.controller;

import com.ism.dto.FlightDto;
import com.ism.dto.RoleDto;
import com.ism.model.Flight;
import com.ism.model.Role;
import com.ism.model.User;
import com.ism.service.RoleService;
import com.ism.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class UserController {


    private final static String ROLE_ERROR_ALREADY_EXIST = "role already exists";
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;



    @PostMapping("/roles")
    public ResponseEntity<String> addRole(@RequestBody RoleDto roleDto) {
        Role checkedRole = roleService.getRoleByName(roleDto.getName());
        if(Objects.nonNull(checkedRole)){
            return ResponseEntity.badRequest().body(ROLE_ERROR_ALREADY_EXIST);
        }

        Role addedRole = roleService.addRole(roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedRole.getName());
    }

}
