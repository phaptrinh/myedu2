package com.example.myedu.security.service;

import com.example.myedu.entity.Role;
import com.example.myedu.entity.User;
import com.example.myedu.repository.RoleRepository;
import com.example.myedu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        UserDetailsImpl userDetails = new UserDetailsImpl();
        int roleId = user.getRoleId();
        Role role = roleRepository.findRoleByRoleId(roleId);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.getRoleName()));

//        return UserDetailsImpl(user.getUserId(),
//                user.getUsername(),
//                user.getPassword(),
//                authorities);
        userDetails.setId(user.getUserId());
        userDetails.setUsername(user.getUsername());
        userDetails.setPassword(user.getPassword());
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
