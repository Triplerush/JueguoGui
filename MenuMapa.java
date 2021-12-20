package JM1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class MenuMapa extends JFrame {
	private static final int ANCHO=200;
	private static final int LARGO=200;
	private JButton opcionMapa;
	private JButton ausuario;
	private JButton busuario;
	
	public MenuMapa() {
		setTitle("Menu del mapa");
		setSize(ANCHO,LARGO);
		setLayout(new BorderLayout());
		contenido();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void contenido() {
		JPanel superior = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel inferior = new JPanel(new FlowLayout());
		JLabel titulo = new JLabel("Datos del mapa");
		
		JLabel titulo = new JLabel("Datos del mapa");

		superior.add(titulo);
		titulo.add(opcionMapa);

		add(titulo,BorderLayout.NORTH);
		add(usuariob,BorderLayout.SOUTH);
	}
}
		