package JM1;
import java.util.*;
public abstract class Soldado {
    static Random rd = new Random();
    //Atributos
    private String nombre;
    private String arma;
    private int nivelAtaque;
    private int nivelDefensa;
    private int nivelVida;
    private boolean vive;
    
    public Soldado(String Nombre){
		nombre = Nombre;
		vive= true;

	}


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public int getNivelAtaque() {
        return nivelAtaque;
    }

    public void setNivelAtaque(int nivelAtaque) {
        this.nivelAtaque = nivelAtaque;
    }

    public int getNivelDefensa() {
        return nivelDefensa;
    }

    public void setNivelDefensa(int nivelDefensa) {
        this.nivelDefensa = nivelDefensa;
    }

    public int getNivelVida() {
        return nivelVida;
    }

    public void setNivelVida(int nivelVida) {
        this.nivelVida = nivelVida;
    }
    
    public boolean isVive() {
        return vive;
    }
    
    public void morir() {
        vive = false;
        nivelVida = 0;
    }
     
    public String toString(){
        return "Nombre :" + this.getNombre() + "\nPuntos de Vida : " + this.getNivelVida() +"\nArma : " + this.getArma() 
                + "\nPuntos de Ataque : " + this.getNivelAtaque()  + "\nPuntos de Defensa : " + this.getNivelDefensa()+"\n\n";
    }
	public abstract int[] getRangoAtaque();
	public abstract String getTipoSoldado();
}