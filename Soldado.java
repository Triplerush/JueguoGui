package JM1;
import java.util.*;
public class Soldado {
    static Random rd = new Random();
    //Atributos
    private String reino;
    private String nombre;
    private String arma;
    private int fila;
    private int columna;
    private int nivelAtaque;
    private int nivelDefensa;
    private int nivelVida;
    private boolean vive;
    private String actitud;
    
    public Soldado(String Nombre){
		nombre = Nombre;
		nivelAtaque = rd.nextInt(5)+1;
		nivelDefensa = rd.nextInt(5)+1;
<<<<<<< HEAD
		nivelVida = rd.nextInt(5)+1;
=======
		nivelVida = rd.nextInt(5)+1;;
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
		vive= true;

	}	
     public  Soldado( String reino , String nombre ,String arma ,int fila, int columna, int nivelAtaque , int nivelDefensa, int nivelVida,String actitud , boolean vive){
        this.reino = reino;
        this.nombre = nombre;
        this.arma = arma;
        this.fila = fila;
        this.columna = columna;
        this.nivelAtaque = nivelAtaque;
        this.nivelDefensa = nivelDefensa;;
        this.nivelVida = nivelVida;
        this.actitud = actitud;
        this.vive = vive;           
    }
     
     public Soldado (){
        this( " ", "(-------)","(sinArma)",0,0,0,0, 0, "-", false);
    }
    public String getReino() {
        return reino;
    }

    public void setReino(String reino) {
        this.reino = reino;
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

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
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

    public void setVive(boolean vive) {
        this.vive = vive;
    }

    public String getActitud() {
        return actitud;
    }

    public void setActitud(String actitud) {
        this.actitud = actitud;
    }
     
    public String toString(){
        return "Nombre :" + this.getNombre() + "\nPuntos de Vida : " + this.getNivelVida() +"\nArma : " + this.getArma() 
                + "\nPuntos de Ataque : " + this.getNivelAtaque()  + "\nPuntos de Defensa : " + this.getNivelDefensa()+"\n\n";
    }
}