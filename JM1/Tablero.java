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
	private JButton datosAtaque;
	private JButton datosDefensa;
	private JButton atacar;
	private JButton moverse;
	private JButton habilidad;
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
		PanelPintado panelUserAtaque= new PanelPintado(territorio.getBases());
		PanelPintado panelUserDefensa = new PanelPintado(territorio.getBases());
		JLabel equipos = new JLabel(equipoAtaque.getReino() + " V.S " + equipoDefensa.getReino());
		mensaje = new JLabel("Turno del Reino Atacante");
		botonUserAtaque = new JButton("Usuario Ataque");
		botonUserDefensa = new JButton("Usuario Defensa");
		generarBotones();

		//Caracteristicas de los componentes
		panelUserAtaque.setLayout(new GridLayout(10,1,10,10));
		panelUserDefensa.setLayout(new GridLayout(10,1,10,10));
		panelUserAtaque.setBorder(new EmptyBorder(5,5,5,5));
		panelUserDefensa.setBorder(new EmptyBorder(5,5,5,5));
		tablero.setBorder(new EmptyBorder(5,5,5,5));
		botonUserAtaque.setSize(LARGO, ANCHO);
		datosAtaque = new JButton("Datos a");
		datosDefensa = new JButton("Datos b");
		opcionMapa = new JButton("Mapa");
		titulo.setBackground(territorio.getMapa());
		tablero.setBackground(territorio.getMapa());
		opcionMapa.setBackground(territorio.getMapa());
		
		//Colocar los compotenentes en la ventana
		titulo.add(equipos);
		titulo.add(mensaje);
		titulo.add(opcionMapa);
		panelUserAtaque.add(botonUserAtaque);
		panelUserDefensa.add(botonUserDefensa);
		panelUserAtaque.add(datosAtaque);
		panelUserDefensa.add(datosDefensa);
		add(titulo,BorderLayout.NORTH);
		add(tablero,BorderLayout.CENTER);
		add(panelUserAtaque,BorderLayout.WEST);
		add(panelUserDefensa,BorderLayout.EAST);
		
		//Colocando los listener
		opcionMapa.addActionListener(new Listener());
		botonUserAtaque.addActionListener(new Listener());
		botonUserDefensa.addActionListener(new Listener());
		datosAtaque.addActionListener(new Listener());
		datosDefensa.addActionListener(new Listener());
		
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
	public MenuExtra opcionFicha() {
		MenuExtra opcionFicha = new MenuExtra("Opciones del " + botonActual.getText());
		JPanel centro  = opcionFicha.getPanelCentro();
		JPanel centroSuperior  = new JPanel();
		JPanel centroInferior  = new JPanel();
		centro.add(centroSuperior);
		centro.add(centroInferior);
		atacar = new JButton("Atacar");
		moverse = new JButton("Moverse");
		habilidad = new JButton("Habilidad");
		Soldado soldadoActual = registroTablero[encontrarPosBoton()[0]][encontrarPosBoton()[1]];
		JTextArea datos = new JTextArea(soldadoActual.toString());
		datos.setEditable(false);   

		
		opcionFicha.setSize(250, 300);
		centroSuperior.setLayout(new GridLayout(3,1,5,5));
		centroSuperior.add(atacar);
		centroSuperior.add(moverse);
		centroSuperior.add(habilidad);
		opcionFicha.add(centro,BorderLayout.CENTER);
		atacar.addActionListener(new Listener());
		moverse.addActionListener(new Listener());
	
		centroInferior.add(datos);
		
		
		
		return opcionFicha;
	}				

	//generacion del soldado por turnos 
	public void generarSoldado() {
		int[] temp = encontrarPosBoton();
		Soldado soldado;
		Reino reino;
		String equipo;
		boolean orientacion;
		
		//Verifico con que reino actuo
		if(turno) {
			reino = equipoAtaque;
			equipo = " - E1";
			orientacion = true;
		}else {
			reino = equipoDefensa;
			equipo = " - E2";
			orientacion = false;
		}
		
		if(invocacion.equals("Caballero")) {
			soldado = reino.generarCaballero(invocacion+equipo);
		}else if(invocacion.equals("Lancero")) {
			soldado = reino.generarLancero(invocacion+equipo,orientacion);
		}else {
			soldado = reino.generarArquero(invocacion+equipo,orientacion);
		}
		
		registroTablero[temp[0]][temp[1]] = soldado ;
		turno = !(turno);
		cambioMensaje();

	}
	
	//Movimiento de la ficha en los lugares pintados de rojo
	public void movimiento(JButton botonMov) {
		botonMov.setText(botonActual.getText());		
		botonMov.setBackground(botonActual.getBackground());
		botonMov.setEnabled(true);
		botonActual.setText("");
		botonActual.setEnabled(!(turno));
		int[] posAnterior = encontrarPosBoton();
		botonActual = botonMov;
		int[] posDespues = encontrarPosBoton();
		registroTablero[posDespues[0]][posDespues[1]] =  registroTablero[posAnterior[0]][posAnterior[1]];
		registroTablero[posAnterior[0]][posAnterior[1]] = null;
		turno = !(turno);
		cambioMensaje();

	}
	
	//Movimiento de la ficha en los lugares pintados de rojo
	public void ataque(JButton botonAtacado) {
		int[] posAtaque = encontrarPosBoton();
		Soldado soldadoAtacante = registroTablero[posAtaque[0]][posAtaque[1]];
		if(soldadoAtacante.getTipoSoldado().equals("Arquero")) {
			
		}
		botonActual = botonAtacado;
		int[] posDefensa = encontrarPosBoton();
		int nivelVidaNuevo = registroTablero[posDefensa[0]][posDefensa[1]].getNivelVida()- soldadoAtacante.getNivelAtaque();
		if(nivelVidaNuevo<=0) {
			registroTablero[posDefensa[0]][posDefensa[1]].morir();
			botonesTablero[posDefensa[0]][posDefensa[1]].setText("");
			registroTablero[posDefensa[0]][posDefensa[1]]= null;
		}else
			registroTablero[posDefensa[0]][posDefensa[1]].setNivelVida(nivelVidaNuevo);
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
	public boolean verificarMonedas(){
		Reino reino;
		if(turno) {
			reino = equipoAtaque;
		}else 
			reino = equipoDefensa;

		if(invocacion.equals("Caballero") && reino.getMonedas()-8>=0) {
			cobrarPrecio(8);
			return true;
		}else if(invocacion.equals("Lancero")&& reino.getMonedas()-10>=0) {
			cobrarPrecio(10);
			return true;
		}else if(invocacion.equals("Arquero")&& reino.getMonedas()-5>=0){
			cobrarPrecio(5);
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
		asignarGanador();
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
	public void pintarCasillaMov(int x,int y) {
		Soldado aux;
		for(int i=x-1; i<x+2;i++) {
			for(int j = y-1; j<y+2;j++) {
				if(i>= 0 && i<10 && j>= 0 && j<10) {
					aux = registroTablero[i][j];
					if(aux == null ){
						botonesTablero[i][j].setBackground(Color.blue);
						botonesTablero[i][j].setEnabled(true);
					}
				}
			}
		}
	}
	//Pinta las casillas alrededor del boton de color rojo
	public void menuGanador(Reino reino) {
		MenuExtra menuGanador = new MenuExtra("!!!El Reino de " + reino.getReino()+" es el Ganador!!!");
		menuGanador.getTitulo().setFont(new Font("Serif",Font.BOLD, 40));
		menuGanador.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//Pinta las casillas alrededor del boton de color rojo
	public void asignarGanador() {
		if(!(verificarSoldados(equipoAtaque)) && equipoAtaque.getMonedas()<=2) {
			menuGanador(equipoDefensa);
		}else if(!(verificarSoldados(equipoDefensa)) && equipoDefensa.getMonedas()<=2) {
			menuGanador(equipoAtaque);
		}
		
	}
	//Pinta las casillas alrededor del boton de color rojo
	public boolean verificarSoldados(Reino reino) {
		for(Soldado a: reino.getSoldados()) {
			if(a.isVive()) {
				return true;
			}
		}
		return false;
	}
	//Pinta las casillas alrededor del boton de color rojo
	public void pintarCasillaAtaque(int x,int y) {
		String equipo = registroTablero[x][y].getNombre().substring(registroTablero[x][y].getNombre().length()-2);
		int[] rango = registroTablero[x][y].getRangoAtaque();
		Soldado aux;

		for(int i=x+rango[1]; i<x+rango[1]+rango[3];i++) {
			for(int j = y+rango[0]; j<y+rango[0]+rango[2];j++) {
				if(i>= 0 && i<10 && j>= 0 && j<10) {
					aux = registroTablero[i][j];
					if(aux == null ){
						botonesTablero[i][j].setBackground(Color.red);
						botonesTablero[i][j].setEnabled(false);
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
			if(aux.getBackground() == Color.blue) {//Verifica si el boton tiene algun soldado
				movimiento(aux);
			}else if(aux.getBackground() == Color.red){
				ataque(aux);
			}else if(aux.getText().equals("")){
				if(!(invocacion.equals(""))) {
					if(verificarMonedas()) {
						botonActual = aux;
						nombrarCasilla(aux);
						generarSoldado();
					}
				}
			}else{
				botonActual = aux;
				menuActual = opcionFicha();
			}
			restablecerTablero();
		}
	}

	//listener para mas elementos de la ventana
	private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == lista) {
				invocacion = (String)lista.getSelectedItem();		
			}else if(e.getSource() == botonUserAtaque){
				opcionUsuario(equipoAtaque);
			}else if(e.getSource() == botonUserDefensa ){
				opcionUsuario(equipoDefensa);
			}else if(e.getSource() == opcionMapa){
				datosMapa();
			}else if(e.getSource() == datosAtaque) {
				datosUsuario(equipoAtaque);
			}else if(e.getSource() == datosDefensa){
				datosUsuario(equipoDefensa);
			}else if(e.getSource() == moverse){
				pintarCasillaMov(encontrarPosBoton()[0],encontrarPosBoton()[1]);
				menuActual.dispose();
			}else if(e.getSource() == atacar){
				pintarCasillaAtaque(encontrarPosBoton()[0],encontrarPosBoton()[1]);
				menuActual.dispose();
			}else if(e.getSource() == moverse){
				pintarCasillaMov(encontrarPosBoton()[0],encontrarPosBoton()[1]);
				menuActual.dispose();
			}else if(e.getSource() == habilidad){
				pintarCasillaAtaque(encontrarPosBoton()[0],encontrarPosBoton()[1]);
				menuActual.dispose();
			}
		}
	}

}