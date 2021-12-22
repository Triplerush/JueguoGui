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
<<<<<<< HEAD
	public void generarArquero(String tipo) {
		Arquero aux = new Arquero(tipo );
		soldados.add(aux);
	}	
	public void generarLancero(String tipo) {
		Lancero aux = new Lancero(tipo);
		soldados.add(aux);
	}	
	public void generarCaballero(String tipo) {
		Caballero aux = new Caballero(tipo);
		soldados.add(aux);
	}	
}
=======
	public void generarSoldado(String tipo) {
		Soldado aux = new Soldado(tipo);
		soldados.add(aux);
	}	
}
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
