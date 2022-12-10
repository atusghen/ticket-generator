package com.unibg.ticketgenerator.security.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

//questa interfaccia definisce il caricamento dei dati aggiuntivi sulla base dell'username
public interface UserDetailsService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
