package com.unibg.ticketgenerator.srv.ope;

import com.unibg.ticketgenerator.dao.TipoARepository;
import com.unibg.ticketgenerator.entities.TipoA;
import com.unibg.ticketgenerator.srv.dto.ObjectCb;
import com.unibg.ticketgenerator.srv.library.BasicOPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service(IncrementaOPE.NAME)
public class IncrementaOPE extends BasicOPE<ObjectCb.I, ObjectCb.O> {

	@Autowired
	protected TipoARepository tipoARepository;

	public static final String NAME = "IncrementaOPE";

	public ObjectCb.O execute(ObjectCb.I i) {

		List<TipoA> pila=tipoARepository.findAll();
		ObjectCb.O o=new ObjectCb.O();
//		operazione nel caso la lista sia vuota
		if(pila.isEmpty())
		{
			o.setBiglietto(new TipoA(1,0,0));
			tipoARepository.insert(new TipoA(1,0,0));
			return o;
		}

//		cerco il max n lista, poi cerco il max id. se per quel n liste è arrivato a 100, aumento nlista e riparto da 0
//		int listaAttuale=IncrementaOPE.listaAttuale(pila.stream().map(n -> n.getNlista()).toList());
//		List<TipoA> maxLista=pila.stream().filter(n -> n.getNlista()==listaAttuale).toList();

		int listaAttuale=IncrementaOPE.listaAttuale(pila.stream().map(n -> n.getNlista()).collect(Collectors.toList()));
		List<TipoA> maxLista=pila.stream().filter(n -> n.getNlista()==listaAttuale).collect(Collectors.toList());

//		operazione nel caso la lista sia oltre il 100, creo una nuova lista incrementando il counter
		if(Collections.max(maxLista).getId()>99)
		{
			o.setBiglietto(new TipoA(1,0,listaAttuale+1));
			tipoARepository.insert(new TipoA(1,0,listaAttuale+1));
			return o;
		}

//		operaione nel caso la lista non sia oltre il 100, incremento la lista attuale
		long index= Collections.max(maxLista).getId();
		o.setBiglietto(new TipoA(index+1,0,listaAttuale));
		tipoARepository.insert(o.getBiglietto());
		return o;
	}

	public static int listaAttuale(List<Integer> nListe)
	{
		return Collections.max(nListe);
	}

}