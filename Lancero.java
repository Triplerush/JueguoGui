package JM1;
import java.util.*;
public class Lancero extends Soldado{
<<<<<<< HEAD
        private int longitudLanza;
=======
    
     //Atributos
    private int longitudLanza;
    
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
    
    public Lancero(String reino , String nombre ,String arma,int fila, int columna, int nivelAtaque , int nivelDefensa, int nivelVida,String actitud , boolean vive){
        super( reino ,  nombre , arma , fila,  columna,  nivelAtaque ,  nivelDefensa, nivelVida, actitud ,  vive);
    }
<<<<<<< HEAD
    public Lancero(String nombre){
        super(nombre);
        setArma("Lanza");
     } 
=======

>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
    public int getLongitudLanza() {
        return longitudLanza;
    }

    public void setLongitudLanza(int longitudLanza) {
        this.longitudLanza = longitudLanza;
    }
    
    public void schiltrom(){
<<<<<<< HEAD
        //Activo su metodo de defensa
=======
        //Activo su método de defensa
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
        this.setNivelDefensa( this.getNivelDefensa() + 1); //Su defensa aumetna en uno
    }
}