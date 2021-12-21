package JM1;
import java.util.*;
public class Arquero extends Soldado{
    //    Atributos
    private int cantFlechas = 20; //Empiezan con 20 flechas
     
     public Arquero(String reino , String nombre ,String arma,int fila, int columna, int nivelAtaque , int nivelDefensa, int nivelVida,String actitud , boolean vive){
        super( reino ,  nombre , arma , fila,  columna,  nivelAtaque ,  nivelDefensa, nivelVida, actitud ,  vive);
    }
     
     public void disparar(){
         this.setNivelAtaque( this.getNivelAtaque() + 1);
         cantFlechas--;
     }
     
      public void atacar(){
       //Distintas opciones
    }
}