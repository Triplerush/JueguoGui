package JM1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;


public class Tablero extends JFrame {
	static Random rd = new Random();
	private static final int ANCHO=800;
	private static final int LARGO=800;
	private JButton opcionMapa;
	private JButton ausuario;
	private JButton busuario;
	private JButton datosA;
	private JButton datosB;
	private Soldado registro[][] = new Soldado[10][10];
	private String territorio;
	private String invocacion = "";
	private JComboBox<String> lista;
	private Reino ataque;
	private Reino defensa;

	
	public Tablero(String territorio,Reino a, Reino b) {
		this.setTerritorio(territorio);
		ataque = a;
		defensa = b;
		setTitle("Juego Batalla");
		setSize(ANCHO,LARGO);
		setLayout(new BorderLayout());
		contenido();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public void contenido() {
		JPanel titulo = new JPanel(new GridLayout(1,2));
		JPanel tablero = new JPanel(new GridLayout(10,10,5,5));
		JPanel usuarioa= new JPanel(new GridLayout(10,1,10,10));
		JPanel usuariob = new JPanel(new GridLayout(10,1,10,10));
		JLabel atitulo = new JLabel(ataque.getReino() + " V.S " + defensa.getReino());
		ausuario = new JButton("Usuario a");
		busuario = new JButton("Usuario b");
		datosA = new JButton("Datos a");
		datosB = new JButton("Datos b");
		opcionMapa = new JButton("Mapa");
		titulo.setBackground(Color.ORANGE);
		usuarioa.setBackground(Color.CYAN);
		usuariob.setBackground(Color.CYAN);
		tablero.setBackground(Color.black);
		opcionMapa.setBackground(Color.blue);
		for (int i = 1;i<=100;i++) {
			JButton boton = new JButton();
		    boton.setBackground(Color.gray);
			tablero.add(boton);
			boton.addActionListener(new ListenerJuego());
		}	
		titulo.add(atitulo);
		titulo.add(opcionMapa);
		usuarioa.add(ausuario);
		usuariob.add(busuario);
		usuarioa.add(datosA);
		usuariob.add(datosB);
		add(titulo,BorderLayout.NORTH);
		add(tablero,BorderLayout.CENTER);
		add(usuarioa,BorderLayout.WEST);
		add(usuariob,BorderLayout.EAST);
		opcionMapa.addActionListener(new ListenerJuego());
		ausuario.addActionListener(new ListenerJuego());
		busuario.addActionListener(new ListenerJuego());
		datosA.addActionListener(new ListenerJuego());
		datosB.addActionListener(new ListenerJuego());
		
	}
	public JButton getMapa() {
		return  opcionMapa;
	}
	public void opcionUsuario() {
		String[] invocaciones = {"Caballero", "Arquero", "Lancero"};
		MenuExtra opcion = new MenuExtra("Opciones del jugador");
		String datos = "1.-Puntos para comprar: " + rd.nextInt(15);
		JLabel datosUsuario = new JLabel(datos);
		JLabel invocar = new JLabel("Soldado para invocar");
		lista = new JComboBox<String>(invocaciones);
		opcion.getPanelSuperior().add(datosUsuario);
		opcion.getPanelCentro().add(invocar);
		opcion.getPanelCentro().add(lista);
		lista.addActionListener(new Listener());
	}
	public void datosUsuario(Reino reino) {
		MenuExtra opcion = new MenuExtra("Datos del jugador");
		String dato = "Ejercito: " + reino.getReino()+"\n"+
					  "Datos de los Soldados para invocar\n\n";
		for(Soldado a: reino.getSoldados()) {
			dato += a.toString() ;
		}
		JTextArea datos = new JTextArea(dato);
		datos.setEditable(false);
		opcion.getPanelCentro().add(datos);
	}
	public void datosMapa() {
		MenuExtra infoMapa = new MenuExtra("Datos del mapa");
		JPanel inferior = new JPanel(new FlowLayout());
		String info = "Mapa:" + territorio;
		JLabel datos = new JLabel(info);
		inferior.add(datos);
		infoMapa.add(inferior,BorderLayout.CENTER);

	}
	public String getTerritorio() {
		return territorio;
	}
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}
	private class ListenerJuego implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(e.getSource() == busuario ){
				opcionUsuario();
			}else if(e.getSource() == opcionMapa){
				datosMapa();
			}else if(e.getSource() == ausuario){
				opcionUsuario();
			}else if(e.getSource() == datosA) {
				datosUsuario(ataque);
			}else if(e.getSource() == datosB){
				datosUsuario(defensa);
			}else if(aux.getText().equals(invocacion)) {
				aux.setText("");
				aux.setBackground(Color.cyan);
			}else{
				aux.setText(invocacion);
				ataque.generarSoldado(invocacion);
				if(invocacion.equals("Caballero")) {
					aux.setBackground(Color.cyan);
				}else if(invocacion.equals("Lancero")) {
					aux.setBackground(Color.RED);
				}else {
					aux.setBackground(Color.ORANGE);
				}
			}	
		}
	}
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == lista) {
				invocacion = (String)lista.getSelectedItem();		
			}
		}
	}
	
}