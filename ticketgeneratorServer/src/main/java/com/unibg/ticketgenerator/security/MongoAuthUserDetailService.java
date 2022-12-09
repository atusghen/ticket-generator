package com.unibg.ticketgenerator.security;

import com.unibg.ticketgenerator.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.HashSet;
import java.util.Set;

@Service
public class MongoAuthUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        com.unibg.ticketgenerator.security.model.User user = userRepository.findUserByUsername(userName);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        user.getAuthorities()
                .forEach(role -> {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()
                            .getName()));
                });

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

}