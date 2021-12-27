package JM1;
import java.util.*;
public class Caballero extends Soldado {
    private boolean esMontado = false; //Al principio inicia desmontado
    private boolean estadoFuria = false ; 
    private int[] rangoAtaque = {-1,-1,3,3};


 
    public Caballero(String nombre){
        super(nombre);
        setArma("Espada");
        setNivelAtaque(5);
        setNivelVida(10);
        setNivelDefensa(8);
    } 
     
    public String getTipoSoldado() {
		return "Arquero";
	}
    
    public void desmontar(){
         if(esMontado)
             this.setArma("Espada");
         else
             System.out.println("No puede realizar esa acci�n, ya esta desmontado");    
    }
    
    public void montar(){
         if(esMontado == false){
             esMontado = true; //Se monta
             this.setArma("Lanza");  
         }
         else
             System.out.println("No puede realizar esa acci�n, ya esta montado");
         
     }
     public void embestir(){
         if(esMontado == false)
              this.setNivelAtaque( this.getNivelAtaque()*2); //Ataca dos veces, esto es como duplicar su da�o

         else
              this.setNivelAtaque( this.getNivelAtaque()*3);//Ataca dos veces, esto es como triplicar su da�o
         
        
     }
     public int[] getRangoAtaque() {
 		return rangoAtaque;
     }
}