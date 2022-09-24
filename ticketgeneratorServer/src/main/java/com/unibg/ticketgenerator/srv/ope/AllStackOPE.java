package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TipoARepository;
import com.unibg.ticketgenerator.entities.TipoA;
import com.unibg.ticketgenerator.srv.dto.ArrayCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(AllStackOPE.NAME)
public class AllStackOPE extends BasicOPE<ArrayCb.I, ArrayCb.O> {
    @Autowired
    protected TipoARepository tipoARepository;

    public static final String NAME = "AllStackOPE";
    public ArrayCb.O execute(ArrayCb.I i) {
        List<TipoA> pila= tipoARepository.findAll();
        ArrayCb.O o=new ArrayCb.O();
        o.setOutput(pila);
        return o;
    }
}

