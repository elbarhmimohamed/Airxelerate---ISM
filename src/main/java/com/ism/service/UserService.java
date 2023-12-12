package com.ism.service;
import com.ism.dto.RoleDto;
import com.ism.dto.UserDto;
import com.ism.mapper.RoleMapper;
import com.ism.mapper.UserMapper;
import com.ism.model.User;
import com.ism.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<UserDto> getUserDtos(){

        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map( u -> userMapper.convertToDto(u))
                .collect(Collectors.toList());

        return userDtos;
    }

    public UserDto getUserById(Long id){
        User user = userRepository.findById(id).orElse(null);
        if(Objects.nonNull(user)){
            return userMapper.convertToDto(user);
        }
        return null;
    }
    public UserDto getUserByUserName(String username){

        User user = userRepository.findByUsername(username).orElse(null);
        if(Objects.nonNull(user)){
            return userMapper.convertToDto(user);
        }
        return null;
    }

    public UserDto addUser(UserDto userDto){
        User user = userMapper.convertToEntity(userDto);
        RoleDto roleDto = roleService.getRoleById(user.getRole().getId());
        user.setRole(roleMapper.convertToEntity(roleDto));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.convertToDto(userRepository.save(user));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
