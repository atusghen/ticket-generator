package com.unibg.ticketgeneratorClientOperator;

public class Ticket {
	//@JsonTypeId
	private long id;
	private long operatore;
	private String pre ="A";

	public Ticket() {}
	public Ticket(long id, int op) {
		this.id=id;
		this.operatore = op;
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
	public String getPre()
	{
		return pre;
	}
	public String toString() {return pre+ ((getId() >10) ? "0" : "00")+ getId();}
	public void assegnaOp(int op){
		this.operatore=op;
	}
}