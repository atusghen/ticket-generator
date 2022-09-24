package com.unibg.ticketgenerator.srv.library;

import com.unibg.ticketgenerator.srv.dto.BasicCB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public abstract class BasicSRV {

    @Autowired
    protected OPEesecutore oe;

    protected ResponseEntity<BasicCB> execOPE(String opeId, BasicCB cb)  {
        try {
            oe.execOPE(opeId,cb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok(cb);
    }
}
