package it.unibg.ticketgenerator.entities;

import org.bson.types.ObjectId;
import org.jetbrains.annotations.NotNull;

//@Document(collection = "ticket")
public class Ticket implements Comparable<Ticket>
{

	private ObjectId _id;
	private long ticketNumber;
	private long operatore;

	private long creationTime;
	private long temp;
	int nLista;

	private TicketType priority;
	private int priorityvalue;
	public Ticket() {}
	public Ticket(long ticketNumber, int op, int nLista) {
		this.ticketNumber = ticketNumber;
		this.operatore = op;
		this.nLista=nLista;
		this.priority =TicketType.C2;
		this.priorityvalue=setPriorityValue(priority);
		this.creationTime=System.currentTimeMillis();
		this.temp=System.currentTimeMillis();
	}
	public Ticket(long ticketNumber, int op, @NotNull TicketType pri, int nLista) {
		this.ticketNumber = ticketNumber;
		this.operatore = op;
		this.nLista=nLista;
		this.priority =pri;
		this.priorityvalue=setPriorityValue(priority);
		this.creationTime=System.currentTimeMillis();
		this.temp=System.currentTimeMillis();
	}


	public Ticket(long ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public long getOperatore() {
		return this.operatore;
	}
	public long getTicketNumber() {
		return ticketNumber;
	}
	public TicketType getPriority()
	{
		return priority;
	}
	public int setPriorityValue(TicketType type){
		if(type==TicketType.A)
			return 4;
		else if(type==TicketType.X)
			return 3;
		else if(type==TicketType.B)
			return 2;
		else if(type==TicketType.C1 || type==TicketType.C2)
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

	public String toString() {return priority +((getTicketNumber() >9) ? "0" : "00")+ getTicketNumber();}
	public void assegnaOp(int op){
		this.operatore=op;
	}
	public void setNlista(int nlista) {this.nLista = nlista;}

	@Override
	public int compareTo(Ticket o) {
		if(o.getTicketNumber()<this.getTicketNumber() && o.getNlista()==this.getNlista())
			return 1;
		else if(o.getTicketNumber()>this.getTicketNumber() && o.getNlista()==this.getNlista())
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