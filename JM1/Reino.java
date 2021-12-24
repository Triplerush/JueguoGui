package JM1;

import java.util.*;

public class Reino {
	private String reino;
	private ArrayList<Soldado> soldados = new  ArrayList<Soldado>();
	
	
	public Reino(String reino){
		this.reino = reino;
	}
	public ArrayList<Soldado> getSoldados() {
		return soldados;
	}
	public String getReino() {
		return reino;
	}
	public void generarSoldados(int a) {
		for(int i = 0; i < a; i++ ) {
			Soldado aux = new Soldado(reino);
			soldados.add(aux);
		}
	}	
	public Arquero generarArquero(String tipo) {
		Arquero aux = new Arquero(tipo );
		soldados.add(aux);
		return aux;
	}	
	public Lancero generarLancero(String tipo) {
		Lancero aux = new Lancero(tipo);
		soldados.add(aux);
		return aux;
	}	
	public Caballero generarCaballero(String tipo) {
		Caballero aux = new Caballero(tipo);
		soldados.add(aux);
		return aux;
	}	
}