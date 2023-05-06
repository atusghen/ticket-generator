package com.unibg.ticketgeneratorVisualizer.entities;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;



public class Ticket implements Comparable<Ticket> {

	private long id;
	private long operatore;

	int nLista;

	private TicketType priority;
	//private String priority;

	private int priorityvalue;
	public Ticket() {}
	public Ticket(long id, int op, int nLista) {
		this.id=id;
		this.operatore = op;
		this.nLista=nLista;
		this.priority=TicketType.X;
		this.priorityvalue=setPriorityValue(TicketType.X);
	}
	public Ticket(long id, int op,  TicketType priority, int nLista) {
		this.id=id;
		this.operatore = op;
		this.nLista=nLista;
		this.priority=priority;
		this.priorityvalue=setPriorityValue(priority);
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

	public String toString() {return priority.toString()+ ((getId() >9) ? "0" : "00")+ getId()+" Operatore"+ ((getOperatore()==0) ? "" : Long.toString(getOperatore()));}
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

	public int setPriorityValue(TicketType type){
		if(type.equals(TicketType.A))
			return 4;
		else if(type.equals(TicketType.X))
			return 3;
		else if(type.equals(TicketType.B))
			return 2;
		else if(type.equals(TicketType.C1) || type.equals(TicketType.C2))
			return 1;
		else
			return 0;
	}

	public int getPriorityvalue(){
		return priorityvalue;
	}


}

