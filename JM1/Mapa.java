package JM1;

import java.awt.Color;

import javax.swing.*;

public class Mapa {
	private String titulo;
	private String texto;
	private ImageIcon imagenPortada ;
	private Color piso;
	private Color casilla;
	private String bases;
	
	
	public Mapa(String titulo, String texto,ImageIcon imagenPortada,Color piso,Color casilla,String bases ) { 
		this.titulo = titulo;
		this.texto = texto;
		this.imagenPortada = imagenPortada;
		this.piso = piso;
		this.casilla = casilla;
		this.bases = bases;
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
		return piso;
	}

	public Color getCasilla() {
		return casilla;
	}

	public String getBases() {
		return bases;
	}
}
