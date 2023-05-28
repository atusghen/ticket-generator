package it.unibg.ticketgenerator.entities;

import org.jetbrains.annotations.NotNull;

//@Document(collection = "ticket")
public class Ticket implements Comparable<Ticket>
{

//	@Id
	private long id;
	private long operatore;

	private long creationTime;
	private long temp;
	int nLista;

	private String priority;
	private int priorityvalue;
	public Ticket() {}
	public Ticket(long id, int op, int nLista) {
		this.id=id;
		this.operatore = op;
		this.nLista=nLista;
		this.priority =TicketType.C2.getLabel();
		this.priorityvalue=setPriorityValue(priority);
		this.creationTime=System.currentTimeMillis();
		this.temp=System.currentTimeMillis();
	}
	public Ticket(long id, int op, @NotNull String pri, int nLista) {
		this.id=id;
		this.operatore = op;
		this.nLista=nLista;
		this.priority =pri;
		this.priorityvalue=setPriorityValue(priority);
		this.creationTime=System.currentTimeMillis();
		this.temp=System.currentTimeMillis();
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
	public String getPriority()
	{
		return priority;
	}
	public int setPriorityValue(String type){
		if(type==TicketType.A.getLabel())
			return 4;
		else if(type==TicketType.X.getLabel())
			return 3;
		else if(type==TicketType.B.getLabel())
			return 2;
		else if(type==TicketType.C1.getLabel() || type==TicketType.C2.getLabel())
			return 1;
		else
			return 0;
	}
	public boolean comparePriority(Ticket ticket){
		 if(this.getPriorityvalue()>ticket.getPriorityvalue() || (this.getPriorityvalue()==ticket.getPriorityvalue() && this.getCreationTime()<ticket.getCreationTime())){
			 return true;
		 }
		 return false;
	}
	public int getPriorityvalue(){
		return priorityvalue;
	}
	public long getCreationTime(){
		return creationTime;
	}
	public int getNlista() {return nLista;}

	public String toString2() {return priority.toString()+ ((getId() >9) ? "0" : "00")+ getId();}
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

	public void isExpired() {
		if (System.currentTimeMillis() - temp > 10000){
			if(this.priorityvalue<10) {
				this.priorityvalue = priorityvalue + 1;
				temp = System.currentTimeMillis();
			}
		}
	}



}