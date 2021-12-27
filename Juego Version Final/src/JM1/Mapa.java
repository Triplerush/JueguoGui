package JM1;

import java.awt.Color;

import javax.swing.*;

public class Mapa {
	private String titulo;
	private String texto;
	private ImageIcon imagenPortada ;
	private Color mapa;
	private Color casilla;
	private String baseAtaque;
	private String baseDefensa;

	
	
	public Mapa(String titulo, String texto,ImageIcon imagenPortada,Color piso,Color casilla,String base1,String base2 ) { 
		this.titulo = titulo;
		this.texto = texto;
		this.imagenPortada = imagenPortada;
		this.mapa = piso;
		this.casilla = casilla;
		this.baseAtaque = base1;
		this.baseDefensa = base2;

	}

	public String getTitulo() {
		return titulo;
	}

	public String getTexto() {
		return texto;
	}

	public ImageIcon getImagenPortada() {
		return imagenPortada;
	}

	public Color getMapa() {
		return mapa;
	}

	public Color getCasilla() {
		return casilla;
	}

	public String getBaseAtaque() {
		return baseAtaque;
	}
	public String getBaseDefensa() {
		return baseDefensa;
	}
}
