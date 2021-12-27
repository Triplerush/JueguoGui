package JM1;

import java.util.*;

public class Reino {
	static Random rd = new Random();
	private int monedas;
	private String reino;
	private ArrayList<Soldado> soldados = new  ArrayList<Soldado>();
	
	
	public Reino(String reino){
		this.reino = reino;
		monedas = 50;
	}
	public ArrayList<Soldado> getSoldados() {
		return soldados;
	}
	public String getReino() {
		return reino;
	}

	public Arquero generarArquero(String tipo,boolean orientacion) {
		Arquero aux = new Arquero(tipo,orientacion);
		soldados.add(aux);
		return aux;
	}	
	public Lancero generarLancero(String tipo,boolean orientacion) {
		Lancero aux = new Lancero(tipo,orientacion);
		soldados.add(aux);
		return aux;
	}	
	public Caballero generarCaballero(String tipo) {
		Caballero aux = new Caballero(tipo);
		soldados.add(aux);
		return aux;
	}
	public int getMonedas() {
		return monedas;
	}
	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}	
	
}