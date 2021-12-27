package JM1;
import java.util.*;
public class Arquero extends Soldado{
    private int[] rangoAtaque = {1,0,5,1};
    private int[] rangoHabilidad = {3,-2,2,5};

    
    private boolean orientacion;
     
     public Arquero(String nombre,boolean orientacion){
         super(nombre);
         setArma("Arco y Flecha");
         this.orientacion = orientacion;
         if(!(orientacion)) {
        	 rangoAtaque[0] = -rangoAtaque[2];
        	 rangoHabilidad[0] = -rangoHabilidad[0]-1;

         }
         setNivelAtaque(3);
         setNivelVida(5);
         setNivelDefensa(3);

      } 
	public int[] getRangoAtaque() {
		return rangoAtaque;
	}
	public String getTipoSoldado() {
		return "Arquero";
	}

	public int[] getRangoHabilidad() {
		return rangoHabilidad;
	}
	

	public void castigo() {
		setArma("Cuchillo de mano");
		rangoAtaque[0] = -1;
		rangoAtaque[1] = -1;
		rangoAtaque[2] = 3;
		rangoAtaque[3] = 3;
		rangoHabilidad[0] = 0;
		rangoHabilidad[1] = 0;
		rangoHabilidad[2] = 0;
		rangoHabilidad[3] = 0;
	}
}