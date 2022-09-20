package com.unibg.ticketgenerator;
public class TipoA {
//	questo dovrebbe essere un CB che teoricamente implementa solo campi per lo scambio di informazioni

	//@JsonTypeId
	private long id;
	private long operatore;
	private String type ="A";

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
		return type;
	}
	public String toString() {return type+ ((getId() >10) ? "0" : "00")+ getId();}
	public void assegnaOp(int op){
		this.operatore=op;
	}
}