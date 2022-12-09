package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(LoginOPE.NAME)
public class LoginOPE implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    public static final String NAME = "LoginOPE";


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        User user = userRepository.findByUsername(userName);
//
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//
//        user.getAuthorities()
//                .forEach(role -> {
//                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()
//                            .getName()));
//                });
//
//        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);

    return null;
    }
}
