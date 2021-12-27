package JM1;
import java.util.*;
public class Lancero extends Soldado{
    private int[] rangoAtaque = {1,-1,4,3};
    private boolean orientacion;

    

    public Lancero(String nombre,boolean orientacion){
        super(nombre);
        setArma("Lanza");
        this.orientacion = orientacion;
        if(!(orientacion)) {
       	 rangoAtaque[0] = -rangoAtaque[2];
        }
        setNivelAtaque(8);
        setNivelVida(8);
        setNivelDefensa(5);
    }
    public String getTipoSoldado() {
		return "Arquero";
	}
	public int[] getRangoAtaque() {
		return rangoAtaque;
	}
	public void setRangoAtaque(int[] rangoAtaque) {
		this.rangoAtaque = rangoAtaque;
	} 
}