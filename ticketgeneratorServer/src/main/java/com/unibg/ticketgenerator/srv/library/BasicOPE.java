package com.unibg.ticketgenerator.srv.library;

import com.unibg.ticketgenerator.srv.ope.exceptions.CfAlreadyRegistered;
import com.unibg.ticketgenerator.srv.ope.exceptions.TicketAlreadyRegistered;
import com.unibg.ticketgenerator.srv.ope.exceptions.UsernameAlreadyExistException;
import com.unibg.ticketgenerator.srv.ope.exceptions.UsernameNotFoundException;
import com.unibg.ticketgenerator.srv.ope.exceptions.WrongPasswordException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;

public abstract class BasicOPE<I,O> {
    // da implementare
    //questo metodo ha bisogno di sapere le eccezioni manuali lanciate
    public abstract O execute(I i) throws NoSuchBeanDefinitionException,TicketAlreadyRegistered, WrongPasswordException, UsernameNotFoundException, UsernameAlreadyExistException, CfAlreadyRegistered;
}
