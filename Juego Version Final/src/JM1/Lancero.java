package JM1;
import java.util.*;
public class Lancero extends Soldado{
    private int[] rangoAtaque = {1,-1,4,3};
    private boolean orientacion;
    private int[] rangoHabilidad = {1,0,10,1};

    

    public Lancero(String nombre,boolean orientacion){
        super(nombre);
        setArma("Lanza");
        this.orientacion = orientacion;
        if(!(orientacion)) {
       	 	rangoAtaque[0] = -rangoAtaque[2];
       	 	rangoHabilidad[0] = -rangoHabilidad[2];
        }
        setNivelAtaque(8);
        setNivelVida(10);
        setNivelDefensa(5);
    }
    public String getTipoSoldado() {
		return "Lancero";
	}
	public int[] getRangoAtaque() {
		return rangoAtaque;
	}
	public void setRangoAtaque(int[] rangoAtaque) {
		this.rangoAtaque = rangoAtaque;
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
		setNivelAtaque(getNivelAtaque()-3);
	}

}