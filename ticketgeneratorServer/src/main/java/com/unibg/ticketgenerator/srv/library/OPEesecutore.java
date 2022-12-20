package com.unibg.ticketgenerator.srv.library;

import com.unibg.ticketgenerator.srv.dto.BasicCB;
import com.unibg.ticketgenerator.srv.ope.exceptions.UsernameNotFoundException;
import com.unibg.ticketgenerator.srv.ope.exceptions.WrongPasswordException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class OPEesecutore {

    @Autowired
    protected ApplicationContext appContext;

    public BasicCB execOPE(String opeId, BasicCB cb) throws WrongPasswordException, UsernameNotFoundException {

        BasicOPE ope=null;

        try {
            ope = (BasicOPE) appContext.getBean(opeId);
        } catch (NoSuchBeanDefinitionException e) {
            System.out.println("non ho trovato il bean dell'OPE");
            return null;
        }

        if (ope == null) {
            System.out.println("l'ope è nulla");
            throw new NoSuchBeanDefinitionException(opeId);
        }

        cb.setO(ope.execute(cb.getI()));
        return cb;
    }
}

