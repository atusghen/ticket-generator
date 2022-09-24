package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TipoARepository;
import com.unibg.ticketgenerator.entities.TipoA;
import com.unibg.ticketgenerator.srv.dto.ObjectCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service(ServeNextOPE.NAME)
public class ServeNextOPE extends BasicOPE<ObjectCb.I, ObjectCb.O> {
    @Autowired
    protected TipoARepository tipoARepository;
    public static final String NAME = "ServeNextOPE";

    public ObjectCb.O execute(ObjectCb.I i) {
        ObjectCb.O out = new ObjectCb.O();
        long op = i.getNumero();
        List<TipoA> pila=tipoARepository.findAll();

//      qui cerco l'oggetto/oggetti da cancellare. da sostituire con un'operazione di reduce/filter
        List<TipoA> toDelete=new ArrayList<>();
        for(TipoA temp:pila)
        {
            if(temp.getOperatore()==op)
            {
                toDelete.add(temp);
            }
        }

//		se trovo un biglietto con un operatore già assegnato, lo rimuovo
        if (pila.stream().anyMatch(n -> n.getOperatore() == op)){
            pila.removeIf(n -> n.getOperatore() == op);
            tipoARepository.deleteAll(toDelete);
        }
//		assegno il primo biglietto all'operatore disponibile
        if (!pila.isEmpty()) {
            Iterator<TipoA> it = pila.iterator();
            while (it.hasNext()) {
                TipoA temp = it.next();
                if (temp.getOperatore() == 0) {
                    temp.assegnaOp((int) op);
//		restituisco il biglietto trovato libero
                    out.setBiglietto(temp);
                    tipoARepository.saveAll(pila);
                    return out;
                }
            }
            tipoARepository.saveAll(pila);
//		non avendo trovato nessun biglietto libero, restituisco null
//		pila.forEach(System.out::println);
                return null;
            }

            return null;
        }
    }
