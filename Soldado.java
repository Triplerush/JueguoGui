package JM1;
import java.util.*;
public class Soldado {
	Random rd  = new Random();
	private String nombre;
	private int nivelAtaque;
	private int nivelDefensa;
	private int nivelVida;
	private boolean vive;
	private int fila;
	private int columna;
	
	public Soldado(int Fila, int Columna){
		nombre = "Soldado";
		nivelAtaque = rd.nextInt(5)+1;
		nivelDefensa = rd.nextInt(5)+1;
		nivelVida = rd.nextInt(5)+1;
		vive= true;
		fila = Fila;
		columna = Columna;

	}
	public Soldado(String Nombre,int Fila,int Columna,int nivelV,String Actitud){
		nombre = Nombre;
		nivelAtaque = rd.nextInt(5)+1;
		nivelDefensa = rd.nextInt(5)+1;
		nivelVida = nivelV;
		vive= true;
		fila = Fila;
		columna = Columna;
	}	
	public Soldado(String Nombre,int Fila,int Columna,int nivelA,int nivelD,int nivelV,String Actitud ){
		nombre = Nombre;
		nivelAtaque = nivelA;
		nivelDefensa = nivelD;
		nivelVida = nivelV;
		vive= true;
		fila = Fila;
		columna = Columna; 
	}
	public void imprimir() {
		System.out.println("Nombre: " + nombre);
		System.out.println("Fila x Columna: (" + fila +", " + columna + ")" );
		System.out.println("Nivel de vida: " + nivelVida);
		System.out.println("Nivel de ataque: " + nivelAtaque);
		System.out.println("Nivel de defensa: " + nivelDefensa);
		System.out.println("Estado: " + vive );
		System.out.println();
	
	}
	public String getNombre() {
		return nombre;
	}
	public void atacar(){
		
	}					
	public void serAtacado(int daño){
		nivelVida-=daño;
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
	public int getFila() {
		return fila;
	}
	public int getColumna() {
		return columna;
	}
}