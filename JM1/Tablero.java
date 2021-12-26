package JM1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class Tablero extends JFrame {
	//Atributos
	private static final int ANCHO=800;
	private static final int LARGO=800;
	private MenuExtra menuActual;
	private JPanel tablero;
	private JButton[][] botonesTablero = new JButton[10][10];
	private Soldado[][] registroTablero = new Soldado[10][10];
	private JButton opcionMapa;
	private JButton botonUserAtaque;
	private JButton botonUserDefensa;
	private JButton datosA;
	private JButton datosB;
	private JButton atacar;
	private JButton botonActual;
	private JLabel mensaje;
	private Mapa territorio;
	private String invocacion = "";
	private JComboBox<String> lista;
	private Reino equipoAtaque;
	private Reino equipoDefensa;
	private boolean turno = true; //Si es verdadero es el turno del quipo atacante y si es falso es turno del defensor

	
	//Contructor del tablero con los reinos llamados para asi poder modificarlos
	public Tablero(Mapa territorio,Reino a, Reino b) {
		this.territorio=territorio;
		equipoAtaque = a;
		equipoDefensa = b;
		setTitle("Juego Batalla");
		setSize(ANCHO,LARGO);
		setLayout(new BorderLayout());
		contenido();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	//Contenido del tablero
	public void contenido() {
		//Componentes del tablero
		JPanel titulo = new JPanel(new GridLayout(1,3));
		tablero = new JPanel(new GridLayout(10,10,5,5));
		JPanel userAtaque= new JPanel(new GridLayout(10,1,10,10));
		JPanel userDefensa = new JPanel(new GridLayout(10,1,10,10));
		JLabel equipos = new JLabel(equipoAtaque.getReino() + " V.S " + equipoDefensa.getReino());
		mensaje = new JLabel("Turno del Reino Atacante");
		botonUserAtaque = new JButton("Usuario Ataque");
		botonUserDefensa = new JButton("Usuario Defensa");
		generarBotones();

		//Caracteristicas de los componentes
		userAtaque.setBorder(new EmptyBorder(5,5,5,5));
		userDefensa.setBorder(new EmptyBorder(5,5,5,5));
		tablero.setBorder(new EmptyBorder(5,5,5,5));
		botonUserAtaque.setSize(LARGO, ANCHO);
		datosA = new JButton("Datos a");
		datosB = new JButton("Datos b");
		opcionMapa = new JButton("Mapa");
		titulo.setBackground(Color.ORANGE);
		userAtaque.setBackground(territorio.getBases());
		userDefensa.setBackground(territorio.getBases());
		tablero.setBackground(territorio.getMapa());
		opcionMapa.setBackground(territorio.getMapa());
		
		//Colocar los compotenentes en la ventana
		titulo.add(equipos);
		titulo.add(mensaje);
		titulo.add(opcionMapa);
		userAtaque.add(botonUserAtaque);
		userDefensa.add(botonUserDefensa);
		userAtaque.add(datosA);
		userDefensa.add(datosB);
		add(titulo,BorderLayout.NORTH);
		add(tablero,BorderLayout.CENTER);
		add(userAtaque,BorderLayout.WEST);
		add(userDefensa,BorderLayout.EAST);
		
		//Colocando los listener
		opcionMapa.addActionListener(new Listener());
		botonUserAtaque.addActionListener(new Listener());
		botonUserDefensa.addActionListener(new Listener());
		datosA.addActionListener(new Listener());
		datosB.addActionListener(new Listener());
		
		restablecerTablero();
	}
	
	//Genera los botones, los coloca en el tablero y para tener control sobre estos los guardo en un arreglo
	public void generarBotones() {
		for (int i = 0;i<10;i++) {
			for (int j = 0;j<10;j++) {
				JButton boton = new JButton();
				tablero.add(boton);
				boton.addActionListener(new ListenerTablero());
				botonesTablero[i][j] = boton;
			}
		}	
	}
	
	//Opciones del usuario, por mientras solo aparece los puntos aleatorios y una lista de las invocaciones
	public void opcionUsuario(Reino reino) {
		String[] invocaciones = {"","Caballero", "Arquero", "Lancero"};//invocaciones
		MenuExtra opcion = new MenuExtra("Opciones del jugador");
		String datos = "1.-Puntos para comprar: " + reino.getMonedas();//puntos
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
        opcion.setResizable(true);
		String dato = "Ejercito: " + reino.getReino()+"\n"+
					  "Datos de los Soldados para invocar\n\n";
		for(Soldado a: reino.getSoldados()) {
			dato += a.toString() ;
		}
	  
        JTextArea datos = new JTextArea(dato,15,20);           
        datos.setEditable(false);   
        JScrollPane scroll = new JScrollPane(datos);
        opcion.getPanelCentro().add(scroll);//Se añade al centro
        opcion.getPanelInferior().add(new JLabel("HELLO"));
	}
	
	//Datos del mapa 
	public void datosMapa() {
		MenuExtra infoMapa = new MenuExtra("INFORMACIÓN DEL MAPA");
		infoMapa.setResizable(false); 
		infoMapa.setSize(570,270);
        
		JLabel datos = new JLabel("");
		JTextArea contexto = new JTextArea();
		JLabel zonaImagen = new JLabel();
        
		zonaImagen.setHorizontalAlignment(SwingConstants.CENTER);
		zonaImagen.setBounds(0, 0, 200, 120);
        
		String info =  territorio.getTitulo();
		datos = new JLabel(info);
    	
		datos.setHorizontalAlignment(SwingConstants.CENTER);
            
		contexto.setText(territorio.getTexto());
		contexto.setEditable(false);
            
		zonaImagen.setIcon(new ImageIcon(territorio.getImagenPortada().getImage().getScaledInstance(zonaImagen.getWidth(), zonaImagen.getHeight(), Image.SCALE_SMOOTH)));

		infoMapa.getPanelSuperior().add(datos);
		infoMapa.getPanelCentro().add(contexto);
		infoMapa.getPanelInferior().add(zonaImagen);   
	}
	
	//opciones de cada boton del tablero, ventana extra al hacer click en un boton con una invocacion
	public MenuExtra opcionFicha(String ficha) {
		MenuExtra opcionFicha = new MenuExtra("Opciones del : " + ficha);
		JPanel centro  = opcionFicha.getPanelCentro();
		atacar = new JButton("Atacar");
		JButton habilidad1 = new JButton("Habilidad 1");
		JButton habilidad2 = new JButton("Habilidad 2");
		
		opcionFicha.setSize(250, 300);
		centro.setLayout(new GridLayout(5,1,5,5));
		centro.add(atacar);
		centro.add(habilidad1);
		centro.add(habilidad2);
		opcionFicha.add(centro,BorderLayout.CENTER);
		atacar.addActionListener(new Listener());
		
		return opcionFicha;
	}				

	//generacion del soldado por turnos 
	public void generarSoldado() {
		int[] temp = encontrarPosBoton();
		Soldado soldado;
		Reino reino;
		String equipo;
		
		//Verifico con que reino actuo
		if(turno) {
			reino = equipoAtaque;
			equipo = " - E1";
		}else {
			reino = equipoDefensa;
			equipo = " - E2";
		}
		
		if(invocacion.equals("Caballero")) {
			soldado = reino.generarCaballero(invocacion+equipo);
		}else if(invocacion.equals("Lancero")) {
			soldado = reino.generarLancero(invocacion+equipo);
		}else {
			soldado = reino.generarArquero(invocacion+equipo);
		}
		
		registroTablero[temp[0]][temp[1]] = soldado ;
		turno = !(turno);
		cambioMensaje();

	}
	
	//Movimiento de la ficha en los lugares pintados de rojo
	public void movimiento(JButton botonMov) {
		botonMov.setText(botonActual.getText());		
		botonMov.setBackground(botonActual.getBackground());
		botonMov.setEnabled(true);;
		botonActual.setText("");
		botonActual.setEnabled(false);;
		int[] posAnterior = encontrarPosBoton();
		botonActual = botonMov;
		int[] posDespues = encontrarPosBoton();
		registroTablero[posDespues[0]][posDespues[1]] =  registroTablero[posAnterior[0]][posAnterior[1]];
		registroTablero[posAnterior[0]][posAnterior[1]] = null;
		turno = !(turno);
		cambioMensaje();

	}
	//Funcion para cambiar el mensaje segun el turno
	public void cambioMensaje() {
		if(turno){
			mensaje.setText("Turno del equipo Atacante");
		}else {
			mensaje.setText("Turno del equipo Defensor");
		}
	}
	//Funcion para descontar las monedas 
	public void cobrarPrecio(int precio) {
		if(turno){
			equipoAtaque.setMonedas(equipoAtaque.getMonedas()-precio);
		}else {
			equipoDefensa.setMonedas(equipoDefensa.getMonedas()-precio);
		}
	}
	//Verifica si el reino si tiene monedas para invocar
	public boolean verificarMonedas() {
		Reino reino;
		if(turno) {
			reino = equipoAtaque;
		}else 
			reino = equipoDefensa;

		if(invocacion.equals("Caballero") && reino.getMonedas()-3>=0) {
			cobrarPrecio(3);
			return true;
		}else if(invocacion.equals("Lancero")&& reino.getMonedas()-5>=0) {
			cobrarPrecio(5);
			return true;
		}else if(invocacion.equals("Arquero")&& reino.getMonedas()-4>=0){
			cobrarPrecio(4);
			return true;
		}else {
			mensaje.setText("Sin monedas necesarias para comprar");
			return false;
		}
	}
	//Cuando se invoque a un soldado este escribe el nombre en la casilla
	public void nombrarCasilla(JButton aux) {
		if(turno) {
			aux.setText(invocacion+" - E1");
		}else {
			aux.setText(invocacion+" - E2");
		}
	}
	//Cuando se invoque a un soldado este metodo pinta de color la casilla dependiendo el soldado
	public void pintarCasilla(JButton aux) {
		if(invocacion.equals("Caballero")) {
			aux.setBackground(Color.cyan);
		}else if(invocacion.equals("Lancero")) {
			aux.setBackground(Color.green);
		}else {
			aux.setBackground(Color.ORANGE);
		}
	}
	//Anulacion de los botones del tablero por turnos 
	public void restablecerTablero() {
		for (int i = 0;i<10;i++) {
			for (int j = 0;j<5;j++) {
				if(registroTablero[i][j] == null) {
					botonesTablero[i][j].setEnabled(turno);
					botonesTablero[i][j].setBackground(territorio.getCasilla());
				}else {
					String nombre = botonesTablero[i][j].getText().substring(0,botonesTablero[i][j].getText().length()-5);
					if(nombre.equals("Caballero")) {
						botonesTablero[i][j].setBackground(Color.cyan);
					}else if(nombre.equals("Lancero")) {
						botonesTablero[i][j].setBackground(Color.green);
					}else {
						botonesTablero[i][j].setBackground(Color.ORANGE);
					}
				}
				if(registroTablero[i][j+5] == null){
					botonesTablero[i][j+5].setEnabled(!(turno));
					botonesTablero[i][j+5].setBackground(territorio.getCasilla());
				}else {
					String nombre = botonesTablero[i][j+5].getText().substring(0,botonesTablero[i][j+5].getText().length()-5);
					if(nombre.equals("Caballero")) {
						botonesTablero[i][j+5].setBackground(Color.cyan);
					}else if(nombre.equals("Lancero")) {
						botonesTablero[i][j+5].setBackground(Color.green);
					}else {
						botonesTablero[i][j+5].setBackground(Color.ORANGE);
					}
				}
				
			}
		}	
	}
	//Funcion para saber la posicion del boton presionado
	public int[] encontrarPosBoton() {
		for(int i=0; i<10;i++) {
			for(int j = 0; j<10;j++) {
				if(botonesTablero[i][j] == botonActual) {
					int[] temp ={i,j};
					return temp;
				}
			}
		}
		return null;
	}
	//Pinta las casillas alrededor del boton de color rojo
	public void pintarCasillaAlrededor(int x,int y) {
		String nombre = registroTablero[x][y].getNombre();
		String equipo = nombre.substring(nombre.length()-2);
		Soldado aux;
		for(int i=x-1; i<x+2;i++) {
			for(int j = y-1; j<y+2;j++) {
				if(i>= 0 && i<10 && j>= 0 && j<10) {
					aux = registroTablero[i][j];
					if(aux == null ){
						botonesTablero[i][j].setBackground(Color.red);
						botonesTablero[i][j].setEnabled(true);
					}else if(!(aux.getNombre().substring(aux.getNombre().length()-2).equals(equipo))){
						botonesTablero[i][j].setBackground(Color.red);
						botonesTablero[i][j].setEnabled(true);	
					}
				}
			}
		}
	}
	//listener solo para los botones del tablero
	private class ListenerTablero implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(aux.getBackground() == Color.red) {//Verifica si el boton tiene algun soldado
				movimiento(aux);
			}else if(aux.getText().equals("")){
				if(!(invocacion.equals(""))) {
					if(verificarMonedas()) {
						botonActual = aux;
						nombrarCasilla(aux);
						pintarCasilla(aux);
						generarSoldado();
					}
				}
			}else{
				botonActual = aux;
				menuActual = opcionFicha(invocacion);
			}
			restablecerTablero();
		}
	}

	//listener para mas elementos de la ventana
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == lista) {
				invocacion = (String)lista.getSelectedItem();		
			}else if(e.getSource() == botonUserDefensa ){
				opcionUsuario(equipoDefensa);
			}else if(e.getSource() == opcionMapa){
				datosMapa();
			}else if(e.getSource() == botonUserAtaque){
				opcionUsuario(equipoAtaque);
			}else if(e.getSource() == datosA) {
				datosUsuario(equipoAtaque);
			}else if(e.getSource() == datosB){
				datosUsuario(equipoDefensa);
			}else if(e.getSource() == atacar){
				pintarCasillaAlrededor(encontrarPosBoton()[0],encontrarPosBoton()[1]);
				menuActual.dispose();
			}
		}
	}
}