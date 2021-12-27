package JM1;
import java.util.*;
public class Caballero extends Soldado {
    private int[] rangoAtaque = {-1,-1,3,3};
    private int[] rangoHabilidad = {-2,-2,5,5};


 
    public Caballero(String nombre){
        super(nombre);
        setArma("Espada");
        setNivelAtaque(5);
        setNivelVida(15);
        setNivelDefensa(8);
    } 
     
    public String getTipoSoldado() {
		return "Caballero";
	}
    public int[] getRangoAtaque() {
 		return rangoAtaque;
    }
    public int[] getRangoHabilidad() {
 		return rangoHabilidad;
 	}
 	public void castigo() {
		setArma("Sin arma");
		rangoAtaque[0] = 0;
		rangoAtaque[1] = 0;
		rangoAtaque[2] = 0;
		rangoAtaque[3] = 0;
		rangoHabilidad[0] = 0;
		rangoHabilidad[1] = 0;
		rangoHabilidad[2] = 0;
		rangoHabilidad[3] = 0;
	}

}