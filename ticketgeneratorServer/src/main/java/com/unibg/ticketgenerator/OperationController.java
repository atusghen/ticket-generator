package com.unibg.ticketgenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class OperationController {

	@Autowired  //teoricamente è un campo che deve essere collegato prima dell'esecuzione di qualsiasi altro codice
//	in pratica viene istanziato implicitamente da spring qualsiasi oggetto contrassegnato da @Autowired
	private OperationService service; //= new OperationService(); questo non serve ci pensa spring a inizializzare
	AtomicLong counter=new AtomicLong();
	ArrayList<TipoA> pila=new ArrayList<>();

//	generazione dei biglietti tipo std
	@GetMapping("/tipoA")
	public TipoA incrementaA() {
		TipoA temp=service.incrementaA(counter);
		pila.add(temp);
		return temp;
	}
	@GetMapping("/allStack")
	public List<TipoA> allStack() {
//		JSONPObject o= new
		return pila;
	}
//	funzione per il client dell'operatore che riceve il numero dal client dell'operatore, lo assegna al biglietto in coda e elimina
//	un'eventuale altro biglietto associato all'operatore se presente
	 @GetMapping("/serveNext")
	public TipoA serveNext(int op) {
//		se trovo un biglietto con un operatore già assegnato, lo rimuovo
//		 System.out.println("questo è l'op "+op);
		if(pila.stream().anyMatch(n -> n.getOperatore()==op));
		 {
			 pila.removeIf(n -> n.getOperatore()==op);
		 }
//		 assegno il primo biglietto all'operatore disponibile
		 if(!pila.isEmpty()) {
			 Iterator<TipoA> i=	pila.iterator();
			 while(i.hasNext()) {
				 TipoA temp = i.next();
				 if (temp.getOperatore() == 0) {
					 temp.assegnaOp(op);
//					 restituisco il biglietto
					 return temp;
				 }
			 }
//			non avendo trovato nessun biglietto libero, restituisco null
//			 pila.forEach(System.out::println);
			 return null;
		 } return null;
	}


}