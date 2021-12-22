package JM1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;


public class Tablero extends JFrame {
<<<<<<< HEAD
	//Atributos
=======
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
	static Random rd = new Random();
	private static final int ANCHO=800;
	private static final int LARGO=800;
	private JPanel tablero;
	private JButton[][] botonesTablero = new JButton[10][10];
	private JButton opcionMapa;
	private JButton ausuario;
	private JButton busuario;
	private JButton datosA;
	private JButton datosB;
	private Soldado registro[][] = new Soldado[10][10];
	private String territorio;
<<<<<<< HEAD
	private String invocacion = "Caballero";
	private JComboBox<String> lista;
	private Reino equipoAtaque;
	private Reino equipoDefensa;
	private boolean turno = true; //Si es verdadero es el turno del quipo atacante y si es falso es turno del defensor

	
	//Contructor del tablero con los reinos llamados para asi poder modificarlos
	public Tablero(String territorio,Reino a, Reino b) {
		this.setTerritorio(territorio);
		equipoAtaque = a;
		equipoDefensa = b;
=======
	private String invocacion = "";
	private JComboBox<String> lista;
	private Reino ataque;
	private Reino defensa;

	
	public Tablero(String territorio,Reino a, Reino b) {
		this.setTerritorio(territorio);
		ataque = a;
		defensa = b;
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
		setTitle("Juego Batalla");
		setSize(ANCHO,LARGO);
		setLayout(new BorderLayout());
		contenido();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//Contenido del tablero
	public void contenido() {
		JPanel titulo = new JPanel(new GridLayout(1,2));
<<<<<<< HEAD
		tablero = new JPanel(new GridLayout(10,10,5,5));
		JPanel usuarioa= new JPanel(new GridLayout(10,1,10,10));
		JPanel usuariob = new JPanel(new GridLayout(10,1,10,10));
		JLabel atitulo = new JLabel(equipoAtaque.getReino() + " V.S " + equipoDefensa.getReino());
=======
		JPanel tablero = new JPanel(new GridLayout(10,10,5,5));
		JPanel usuarioa= new JPanel(new GridLayout(10,1,10,10));
		JPanel usuariob = new JPanel(new GridLayout(10,1,10,10));
		JLabel atitulo = new JLabel(ataque.getReino() + " V.S " + defensa.getReino());
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
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
<<<<<<< HEAD
		generarBotones();
=======
		for (int i = 1;i<=100;i++) {
			JButton boton = new JButton();
		    boton.setBackground(Color.gray);
			tablero.add(boton);
			boton.addActionListener(new ListenerJuego());
		}	
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
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
<<<<<<< HEAD
		opcionMapa.addActionListener(new Listener());
		ausuario.addActionListener(new Listener());
		busuario.addActionListener(new Listener());
		datosA.addActionListener(new Listener());
		datosB.addActionListener(new Listener());
		anularTablero();

	}
	
	//Genera los botones, los coloca en el tablero y para tener control sobre estos los guardo en un arreglo
	public void generarBotones() {
		for (int i = 0;i<10;i++) {
			for (int j = 0;j<10;j++) {
				JButton boton = new JButton();
				boton.setBackground(Color.gray);
				tablero.add(boton);
				boton.addActionListener(new ListenerTablero());
				botonesTablero[i][j] = boton;
			}
		}	
	}
	
	//Opciones del usuario, por mientras solo aparece los puntos aleatorios y una lista de las invocaciones
	public void opcionUsuario() {
		String[] invocaciones = {"Caballero", "Arquero", "Lancero"};//invocaciones
		MenuExtra opcion = new MenuExtra("Opciones del jugador");
		String datos = "1.-Puntos para comprar: " + rd.nextInt(15);//puntos
		JLabel datosUsuario = new JLabel(datos);
		JLabel invocar = new JLabel("Soldado para invocar");
		lista = new JComboBox<String>(invocaciones);//lista 
		opcion.getPanelSuperior().add(datosUsuario);
		opcion.getPanelCentro().add(invocar);
		opcion.getPanelCentro().add(lista);
		lista.addActionListener(new Listener());
	}
	
	//Datos del usuario como los soldados que tiene
	public void datosUsuario(Reino reino) {
		MenuExtra opcion = new MenuExtra("Datos del jugador");
		String dato = "Ejercito: " + reino.getReino()+"\n"+
					  "Datos de los Soldados para invocar\n\n";
		for(Soldado a: reino.getSoldados()) {
			dato += a.toString() ;
		}
		JTextArea datos = new JTextArea(dato);//textarea para los soldados
		datos.setEditable(false);
		opcion.getPanelCentro().add(datos);
=======
		opcionMapa.addActionListener(new ListenerJuego());
		ausuario.addActionListener(new ListenerJuego());
		busuario.addActionListener(new ListenerJuego());
		datosA.addActionListener(new ListenerJuego());
		datosB.addActionListener(new ListenerJuego());
		
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
	}
	
	//Datos del mapa 
	public void datosMapa() {
		MenuExtra infoMapa = new MenuExtra("Datos del mapa");
		JPanel centro  = infoMapa.getPanelCentro();
		String info = "Mapa : " + territorio;
		JLabel datos = new JLabel(info);
		centro.add(datos);
		infoMapa.add(centro,BorderLayout.CENTER);

	}
	
	//opciones de cada boton del tablero, ventana extra al hacer click en un boton con una invocacion
	public void opcionFicha(String ficha) {
		MenuExtra opcionFicha = new MenuExtra("Opciones del : " + ficha);
		JPanel centro  = opcionFicha.getPanelCentro();
		JButton atacar = new JButton("Atacar");
		JButton habilidad1 = new JButton("Habilidad 1");
		JButton habilidad2 = new JButton("Habilidad 2");;
		centro.setLayout(new GridLayout(5,1,5,5));
		centro.add(atacar);
		centro.add(habilidad1);
		centro.add(habilidad2);
		opcionFicha.add(centro,BorderLayout.CENTER);

	}
	
	//Cuando se invoque a un soldado este metodo pinta de color la casilla dependiendo el soldado
	public void pintarCasilla(JButton aux) {
		aux.setText(invocacion);
		if(invocacion.equals("Caballero")) {
			aux.setBackground(Color.cyan);
		}else if(invocacion.equals("Lancero")) {
			aux.setBackground(Color.RED);
		}else {
			aux.setBackground(Color.ORANGE);
		}
	}
	
	//generacion del soldado por turnos 
	public void generarSoldadoEsp() {
		if(turno){
			if(invocacion.equals("Caballero")) {
				equipoAtaque.generarCaballero(invocacion);
			}else if(invocacion.equals("Lancero")) {
				equipoAtaque.generarLancero(invocacion);
			}else {
				equipoAtaque.generarArquero(invocacion);
			}
		}else {
			if(invocacion.equals("Caballero")) {
				equipoDefensa.generarCaballero(invocacion);
			}else if(invocacion.equals("Lancero")) {
				equipoDefensa.generarLancero(invocacion);
			}else {
				equipoDefensa.generarArquero(invocacion);
			}
		}
		turno = !(turno);
	}
	
	//Anulacion de los botones del tablero por turnos 
	public void anularTablero() {
		for (int i = 0;i<10;i++) {
			for (int j = 0;j<5;j++) {
				botonesTablero[i][j].setEnabled(turno);
				botonesTablero[i][j+5].setEnabled(!(turno));
			}
		}		
	}
	
	public JButton getMapa() {
		return  opcionMapa;
	}
<<<<<<< HEAD
	
	public String getTerritorio() {
		return territorio;
	}
	
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}
	
	//listener solo para los botones del tablero
	private class ListenerTablero implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(aux.getText().equals(invocacion)) {//Verifica si el boton tiene algun soldado
				opcionFicha(invocacion);
			}else{
				pintarCasilla(aux);
				generarSoldadoEsp();
				anularTablero();

=======
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
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
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
	
	//listener para mas elementos de la ventana
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == lista) {
				invocacion = (String)lista.getSelectedItem();		
			}else if(e.getSource() == busuario ){
				opcionUsuario();
			}else if(e.getSource() == opcionMapa){
				datosMapa();
			}else if(e.getSource() == ausuario){
				opcionUsuario();
			}else if(e.getSource() == datosA) {
				datosUsuario(equipoAtaque);
			}else if(e.getSource() == datosB){
				datosUsuario(equipoDefensa);
			}
		}
	}
}