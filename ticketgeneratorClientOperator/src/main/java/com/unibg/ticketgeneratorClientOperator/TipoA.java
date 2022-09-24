package com.unibg.ticketgeneratorClientOperator;

public class TipoA {
	//@JsonTypeId
	private long id;
	private long operatore;
	private String pre ="A";

	public TipoA() {}
	public TipoA(long id, int op) {
		this.id=id;
		this.operatore = op;
	}
	public TipoA(long id) {
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