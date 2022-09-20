package com.unibg.ticketgenerator;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class OperationService {
//	contiene al suo interno il tipo di input/output in base all'operazione che svolge
//	inizializza anche il DAO, ovvero la classe che si occupa della comunicazione con il dbms
//	che poi utilizza richiamandone i metodi all'interno dei suoi metodi di esecuzione
//	i suoi metodi restituiscono un CB concorde al tipo di risposta che serve al service in questione (con un input dello stesso tipo)
	public TipoA incrementaA(AtomicLong id) {
		return new Incrementa(id.incrementAndGet()).getNum();
	}


}