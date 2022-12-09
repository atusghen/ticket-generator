package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TipoARepository;
import com.unibg.ticketgenerator.dao.UtenteRepository;
import com.unibg.ticketgenerator.entities.TipoA;
import com.unibg.ticketgenerator.entities.Utente;
import com.unibg.ticketgenerator.srv.dto.ObjectCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service(AggiungiUtenteOPE.NAME)
public class AggiungiUtenteOPE extends BasicOPE<ObjectCb.I, ObjectCb.O> {

    @Autowired
    protected UtenteRepository utenteRepository;

    public static final String NAME = "AggiungiUtenteOPE";

    public ObjectCb.O execute(ObjectCb.I i) {

        List<Utente> pila=utenteRepository.findAll();
        ObjectCb.O o=new ObjectCb.O();
//		operazione nel caso la lista sia vuota

        o.setUtente(new Utente(pila.size(), i.getNome(),i.getCognome(), i.getCf()));
        utenteRepository.insert(new Utente(pila.size(), i.getNome(),i.getCognome(), i.getCf()));
        return o;

//		cerco il max n lista, poi cerco il max id. se per quel n liste è arrivato a 100, aumento nlista e riparto da 0
        //int listaAttuale=AggiungiUtenteOPE.listaAttuale(pila.stream().map(n -> n.getNlista()).toList());
        //List<Utente> maxLista=pila.stream().filter(n -> n.getNlista()==listaAttuale).toList();

//		operazione nel caso la lista sia oltre il 100, creo una nuova lista incrementando il counter
        /*if(Collections.max(maxLista).getId()>99)
        {
            o.setBiglietto(new TipoA(1,0,listaAttuale+1));
            utenteRepository.insert(new Utente(1,0,listaAttuale+1));
            return o;
        }*/

//		operaione nel caso la lista non sia oltre il 100, incremento la lista attuale
        //long index= Collections.max(maxLista).getId();
        //o.setUtente(new Utente(index+1,"Francesco",listaAttuale));
        //utenteRepository.insert(o.getUtente());
        //return o;
    }

}