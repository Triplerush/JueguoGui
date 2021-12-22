package JM1;
import java.util.*;
public class Caballero extends Soldado {
<<<<<<< HEAD
=======
	//Atributo
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
    private boolean esMontado = false; //Al principio inicia desmontado
    private boolean estadoFuria = false ; 
    

    public Caballero(String reino , String nombre ,String arma,int fila, int columna, int nivelAtaque , int nivelDefensa, int nivelVida,String actitud , boolean vive){
       super( reino ,  nombre , arma , fila,  columna,  nivelAtaque ,  nivelDefensa, nivelVida, actitud ,  vive);
<<<<<<< HEAD
    }  
    public Caballero(String nombre){
        super(nombre);
        setArma("Espada");
     } 
=======
    }    
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
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
<<<<<<< HEAD
             System.out.println("No puede realizar esa acciï¿½n, ya esta desmontado");
=======
             System.out.println("No puede realizar esa acción, ya esta desmontado");
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
         }
     }
     
     public void montar(){
         if(esMontado == false){
             esMontado = true; //Se monta
             this.setArma("Lanza");
             
         }
         else{
<<<<<<< HEAD
             System.out.println("No puede realizar esa acciï¿½n, ya esta montado");
         }
     }

     public void embestir(){
         if(esMontado == false){
              this.setNivelAtaque( this.getNivelAtaque()*2); //Ataca dos veces, esto es como duplicar su daï¿½o
         }
         if(esMontado == true){
              this.setNivelAtaque( this.getNivelAtaque()*3);//Ataca dos veces, esto es como triplicar su daï¿½o
         }
        
     }    
}
=======
             System.out.println("No puede realizar esa acción, ya esta montado");
         }
     }
     
     public void embestir(){
         if(esMontado == false){
              this.setNivelAtaque( this.getNivelAtaque()*2); //Ataca dos veces, esto es como duplicar su daño
         }
         if(esMontado == true){
              this.setNivelAtaque( this.getNivelAtaque()*3);//Ataca dos veces, esto es como triplicar su daño
         }
        
     }    
}
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
