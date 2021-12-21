package JM1;
import java.util.*;
public class Lancero extends Soldado{
    
     //Atributos
    private int longitudLanza;
    
    
    public Lancero(String reino , String nombre ,String arma,int fila, int columna, int nivelAtaque , int nivelDefensa, int nivelVida,String actitud , boolean vive){
        super( reino ,  nombre , arma , fila,  columna,  nivelAtaque ,  nivelDefensa, nivelVida, actitud ,  vive);
    }

    public int getLongitudLanza() {
        return longitudLanza;
    }

    public void setLongitudLanza(int longitudLanza) {
        this.longitudLanza = longitudLanza;
    }
    
    public void schiltrom(){
        //Activo su método de defensa
        this.setNivelDefensa( this.getNivelDefensa() + 1); //Su defensa aumetna en uno
    }
}