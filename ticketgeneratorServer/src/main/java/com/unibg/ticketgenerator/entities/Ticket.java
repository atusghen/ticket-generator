package com.unibg.ticketgenerator.entities;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "ticket")
public class Ticket implements Comparable<Ticket> {

	@Id
	private long id;
	private long operatore;

	int nLista;

	private TicketType priority;
	//private String priority;

	public Ticket() {}
	public Ticket(long id, int op, int nLista) {
		this.id=id;
		this.operatore = op;
		this.nLista=nLista;
		this.priority=TicketType.X;
	}
	public Ticket(long id, int op, @NotNull TicketType priority, int nLista) {
		this.id=id;
		this.operatore = op;
		this.nLista=nLista;
		this.priority=priority;
	}
	public Ticket(long id) {
		this.id=id;
	}
	public long getOperatore() {
		return this.operatore;
	}
	public long getId() {
		return id;
	}
	public TicketType getPriority()
	{
		return priority;
	}

	public int getNlista() {return nLista;}

	public String toString() {return priority.toString()+ ((getId() >9) ? "0" : "00")+ getId();}
	public void assegnaOp(int op){
		this.operatore=op;
	}
	public void setNlista(int nlista) {this.nLista = nlista;}

	@Override
	public int compareTo(Ticket o) {
		if(o.getId()<this.getId() && o.getNlista()==this.getNlista())
			return 1;
		else if(o.getId()>this.getId() && o.getNlista()==this.getNlista())
			return -1;
		else //if(o.getNlista()==this.getNlista())
			return 0;
	}



}