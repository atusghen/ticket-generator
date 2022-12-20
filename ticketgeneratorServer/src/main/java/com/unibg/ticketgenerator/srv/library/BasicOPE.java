package com.unibg.ticketgenerator.srv.library;

import com.unibg.ticketgenerator.srv.ope.exceptions.UsernameNotFoundException;
import com.unibg.ticketgenerator.srv.ope.exceptions.WrongPasswordException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

public abstract class BasicOPE<I,O> {
    // da implementare
    public abstract O execute(I i) throws NoSuchBeanDefinitionException, WrongPasswordException, UsernameNotFoundException;
}
