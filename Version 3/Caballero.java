package JM1;
import java.util.*;
public class Caballero extends Soldado {
    private boolean esMontado = false; //Al principio inicia desmontado
    private boolean estadoFuria = false ; 
    

    public Caballero(String reino , String nombre ,String arma,int fila, int columna, int nivelAtaque , int nivelDefensa, int nivelVida,String actitud , boolean vive){
       super( reino ,  nombre , arma , fila,  columna,  nivelAtaque ,  nivelDefensa, nivelVida, actitud ,  vive);
    }  
    public Caballero(String nombre){
        super(nombre);
        setArma("Espada");
     } 
    public void alternarArma(){
       if( this.getArma().equals("Espada") ){
          this.setArma("Lanza");
       }      
       if (this.getArma().equals("Lanza") ){
            this.setArma("Espada");
        }
    }    
    public void desmontar(){
         if(esMontado){
             this.setActitud("Defensa");
             this.setArma("Espada");
         }
         else{
             System.out.println("No puede realizar esa acci�n, ya esta desmontado");
         }
     }
     
     public void montar(){
         if(esMontado == false){
             esMontado = true; //Se monta
             this.setArma("Lanza");
             
         }
         else{
             System.out.println("No puede realizar esa acci�n, ya esta montado");
         }
     }

     public void embestir(){
         if(esMontado == false){
              this.setNivelAtaque( this.getNivelAtaque()*2); //Ataca dos veces, esto es como duplicar su da�o
         }
         if(esMontado == true){
              this.setNivelAtaque( this.getNivelAtaque()*3);//Ataca dos veces, esto es como triplicar su da�o
         }
        
     }    
}