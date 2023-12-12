package com.ism.service;
import com.ism.dto.UserDto;
import com.ism.mapper.UserMapper;
import com.ism.model.Role;
import com.ism.model.User;
import com.ism.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    public User getUserByUserName(String username){
        return userRepository.findByUsername(username) .orElse(null);
    }

    public UserDto addUser(UserDto userDto){
        User user = userMapper.convertToEntity(userDto);
        Role role = roleService.getRoleById(user.getRole().getId());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.convertToDto(userRepository.save(user));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
    /*
    public String BCryptPasswordEncod(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return  encodedPassword;
    }
    */


}
