package com.ism.model;


import com.ism.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority role = new SimpleGrantedAuthority(user.getRole().getName());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(role);
        return authorities;
    }

 

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getCurrentuser(){return  user;}



    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return user.getPassword();
    }



    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return user.getUsername();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CustomUserDetails(User user) {
        this.user = user;
    }
}
