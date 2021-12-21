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
	public void generarSoldado(String tipo) {
		Soldado aux = new Soldado(tipo);
		soldados.add(aux);
	}	
}
