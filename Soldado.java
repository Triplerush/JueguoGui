package JM1;
import java.util.*;
public class Soldado {
	Random rd  = new Random();
	private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int nivelVida;
	private boolean vive;

	
	public Soldado(){
		nombre = "Soldado";
		nivelAtaque = rd.nextInt(5)+1;
		nivelDefensa = rd.nextInt(5)+1;
		nivelVida = rd.nextInt(5)+1;
		vive= true;


	}
	public Soldado(String Nombre){
		nombre = Nombre;
		nivelAtaque = rd.nextInt(5)+1;
		nivelDefensa = rd.nextInt(5)+1;
		nivelVida = rd.nextInt(5)+1;;
		vive= true;

	}	
	public Soldado(String Nombre,int Fila,int Columna,int nivelA,int nivelD,int nivelV,String Actitud ){
		nombre = Nombre;
		nivelAtaque = nivelA;
		nivelDefensa = nivelD;
		nivelVida = nivelV;
		vive= true;
	
	}
	public String toString() {
		return "Nombre: " + nombre +
			   "\nNivel de vida: " + nivelVida+
			   "\nNivel de ataque: " + nivelAtaque+
			   "\nNivel de defensa: " + nivelDefensa+
			   "\nEstado: " + vive +"\n\n";
	
	}
	public String getNombre() {
		return nombre;
	}
	public void atacar(){
		
	}					
	public void serAtacado(int dano){
		nivelVida-=dano;
		if(nivelVida==0) 
			morir();
	}
	public void morir(){
		vive = false;
		nivelVida = 0;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setVidaActual(int vida) {
		nivelVida = vida;
	}
	public int getAtaque() {
		return nivelAtaque;
	}
	public int getDefensa() {
		return nivelDefensa;
	}
	public int getVidaActual() {
		return nivelVida;
	}
	public boolean getEstado(){
		return vive;
	}

}