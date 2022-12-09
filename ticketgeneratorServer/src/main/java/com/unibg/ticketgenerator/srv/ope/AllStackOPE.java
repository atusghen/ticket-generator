package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TipoARepository;
import com.unibg.ticketgenerator.dao.UserRepository;
import com.unibg.ticketgenerator.entities.TipoA;
import com.unibg.ticketgenerator.srv.dto.ArrayCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(AllStackOPE.NAME)
public class AllStackOPE extends BasicOPE<ArrayCb.I, ArrayCb.O> {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    protected TipoARepository tipoARepository;

    @Autowired
    protected UserRepository userRepository;

    public static final String NAME = "AllStackOPE";
    public ArrayCb.O execute(ArrayCb.I i) {
//        Role role = new Role();
//        UserRole userRole = new UserRole();
//        Set<UserRole> grantedAuthorities = new HashSet<>();
//
//
//        role.setName("ROLE_ADMIN");
//        userRole.setRole(role);
//
//        grantedAuthorities.add(userRole);
//
//        role = new Role();
//        userRole = new UserRole();
//        role.setName("ROLE_USER");
//        userRole.setRole(role);
//        grantedAuthorities.add(userRole);
//
//        role = new Role();
//        userRole = new UserRole();
//        role.setName("ROLE_MODERATOR");
//        userRole.setRole(role);
//        grantedAuthorities.add(userRole);
//
//        User user=new User(); user.setUsername("greco"); user.setPassword(bCryptPasswordEncoder.encode("password")); user.setUserRoles(grantedAuthorities);
//        userRepository.insert(user);

        List<TipoA> pila= tipoARepository.findAll();
        ArrayCb.O o=new ArrayCb.O();
        o.setOutput(pila);
        return o;
    }
}

