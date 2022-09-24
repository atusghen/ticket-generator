package com.unibg.ticketgeneratorVisualizer.entities;

//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;

//@Document
public class TipoA implements Comparable<TipoA> {

//	@Id
	private long id;
	private long operatore;

	int nLista;
	private String type ="A";

	public TipoA() {}
	public TipoA(long id, int op, int nLista) {
		this.id=id;
		this.operatore = op;
		this.nLista=nLista;
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

	public int getNlista() {return nLista;}

	public String toString() {return type+ ((getId() >9) ? "0" : "00")+ getId()+" Operatore"+ ((getOperatore()==0) ? "" : Long.toString(getOperatore()));}
	public void assegnaOp(int op){
		this.operatore=op;
	}
	public void setNlista(int nlista) {this.nLista = nlista;}

	@Override
	public int compareTo(TipoA o) {
		if(o.getId()<this.getId() && o.getNlista()==this.getNlista())
			return 1;
		else if(o.getId()>this.getId() && o.getNlista()==this.getNlista())
			return -1;
		else //if(o.getNlista()==this.getNlista())
			return 0;
	}

}