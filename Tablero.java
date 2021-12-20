package JM1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class Tablero extends JFrame {
	private static final int ANCHO=800;
	private static final int LARGO=800;
	private JButton opcionMapa;
	private JButton ausuario;
	private JButton busuario;
	
	public Tablero() {
		setTitle("Juego Batalla");
		setSize(ANCHO,LARGO);
		setLayout(new BorderLayout());
		contenido();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void contenido() {
		JPanel titulo = new JPanel(new GridLayout(1,2));
		JPanel tablero = new JPanel(new GridLayout(10,10));
		JPanel usuarioa= new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel usuariob = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel atitulo = new JLabel("Juego ");
		ausuario = new JButton("Usuario a");
		busuario = new JButton("Usuario b");
		opcionMapa = new JButton("Mapa");
		for (int i = 1;i<=100;i++) {
			JButton boton = new JButton();
			tablero.add(boton);
			boton.addActionListener(new Listener());
		}	
		titulo.add(atitulo);
		titulo.add(opcionMapa);
		usuarioa.add(ausuario);
		usuariob.add(busuario);
		add(titulo,BorderLayout.NORTH);
		add(tablero);
		add(usuarioa,BorderLayout.WEST);
		add(usuariob,BorderLayout.EAST);
		opcionMapa.addActionListener(new Listener());
		ausuario.addActionListener(new Listener());
		busuario.addActionListener(new Listener());
		
	}
	public JButton getMapa() {
		return  opcionMapa;
	}
	public void datosMapa() {
		JPanel tablero = new JPanel(new GridLayout(10,10));
	}
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(e.getSource() == busuario){
				JOptionPane.showMessageDialog(null,"Opciones Usuario b");
			}else if(e.getSource() == opcionMapa){
				datosMapa();
			}else if(e.getSource() == ausuario){
				JOptionPane.showMessageDialog(null,"Opciones Usuario a ");
			}else if(aux.getText().equals("Invocacion")) {
				aux.setText("");
			}else{
				aux.setText("Invocacion");
			}	
		}
	}
	
}