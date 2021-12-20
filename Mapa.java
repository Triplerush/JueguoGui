package JM1;


public class Mapa {
	private Soldado registro[][] = new Soldado[10][10];
	private String territorio;
	private Tablero tablero = new Tablero();
	public Mapa(String territorio) {
		this.territorio = territorio;
	}
	public String getTerritorio() {
		return territorio;
	}
	public Soldado[][] getRegistro() {
		return registro;
	}
	public Tablero getTablero() {
		return tablero;
	}
}