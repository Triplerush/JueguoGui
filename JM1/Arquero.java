package JM1;
import java.util.*;
public class Arquero extends Soldado{
    private int cantFlechas = 10; 
    private int[] rangoAtaque = {1,0,5,1};
    private boolean orientacion;
     
     public Arquero(String nombre,boolean orientacion){
         super(nombre);
         setArma("Arco y Flecha");
         this.orientacion = orientacion;
         if(!(orientacion)) {
        	 rangoAtaque[0] = -rangoAtaque[2];
         }
         setNivelAtaque(3);
         setNivelVida(5);
         setNivelDefensa(3);

      } 
    public void disparar(){
         setCantFlechas(getCantFlechas() - 1);
    }
     
    public void atacar(){
    	cantFlechas--;
    }
	public int[] getRangoAtaque() {
		return rangoAtaque;
	}
	public String getTipoSoldado() {
		return "Arquero";
	}
	public int getCantFlechas() {
		return cantFlechas;
	}
	public void setCantFlechas(int cantFlechas) {
		this.cantFlechas = cantFlechas;
	}

}