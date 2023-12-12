package com.ism.mapper;


import com.ism.dto.FlightDto;
import com.ism.dto.RoleDto;
import com.ism.dto.UserDto;
import com.ism.model.Flight;
import com.ism.model.Role;
import com.ism.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleMapper roleMapper;

    public UserDto convertToDto(User user) {

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(roleMapper.convertToDto(user.getRole()));

        return userDto;
    }

    public User convertToEntity(UserDto userDto){

        User user = new User();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(roleMapper.convertToEntity(userDto.getRole()));

        return user;
    }
}
