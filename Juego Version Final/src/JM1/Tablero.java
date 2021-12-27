package JM1;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;


public class Tablero extends JFrame {
	//Atributos
	private static final int ANCHO=800;
	private static final int LARGO=800;
	private ArrayList<JButton> atacadosHabilidad = new ArrayList<JButton>();
	private MenuExtra menuActual;
	private JPanel tablero;
	private JTextArea informacion;
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
	private JTextArea datos;
	private String invocacion = "";
	private JComboBox<String> lista;
	private Reino equipoAtaque;
	private Reino equipoDefensa;
	private Color colorAtaque = new Color(242,43,45);
	private Color colorHabilidad = new Color(1,228,221);
	private Color colorMovimiento = new Color(9,225,111);
	private Color colorMenu = new Color(1,39,56);

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
		this.setExtendedState(MAXIMIZED_BOTH);
		//Componentes del tablero
		JPanel titulo = new JPanel(new GridLayout(1,3));
		tablero = new JPanel(new GridLayout(10,10,5,5));
		PanelPintado panelUserAtaque= new PanelPintado(territorio.getBaseAtaque());
		PanelPintado panelUserDefensa = new PanelPintado(territorio.getBaseDefensa());
		JLabel equipos = new JLabel(equipoAtaque.getReino() + " V.S " + equipoDefensa.getReino());
		mensaje = new JLabel("Turno del Reino Atacante");
		datosAtaque = new JButton("Datos a");
		datosDefensa = new JButton("Datos b");
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
	
		opcionMapa = new JButton("Mapa");
		botonUserAtaque.setBackground(territorio.getCasilla());
		botonUserDefensa.setBackground(territorio.getCasilla());
		titulo.setBackground(territorio.getMapa());
		tablero.setBackground(territorio.getMapa());
		opcionMapa.setBackground(territorio.getMapa());
		opcionMapa.setForeground(territorio.getCasilla());
		mensaje.setForeground(territorio.getCasilla());
		equipos.setForeground(territorio.getCasilla());
		datosAtaque.setBackground(territorio.getCasilla());
		datosDefensa.setBackground(territorio.getCasilla());

		
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
		opcion.setSize(490,400);
		opcion.getPanelSuperior().setBackground(colorMenu);
		opcion.getPanelCentro().setBackground(colorMenu);
		opcion.getPanelInferior().setBackground(colorMenu);
		String monedas = "1.-Monedas para comprar: " + reino.getMonedas();//puntos
		JLabel datosUsuario = new JLabel(monedas);
		JLabel invocar = new JLabel("Soldado para invocar");
		JPanel centroSuperior = new JPanel(new GridLayout(3,1));
		JPanel centroInferior = new JPanel();
		informacion = new JTextArea();
		informacion.setEditable(false); 
		informacion.setFont(new Font("Serif",Font.BOLD, 15));
		lista = new JComboBox<String>(invocaciones);//lista 

		centroSuperior.setBackground(colorMenu);
		centroInferior.setBackground(colorMenu);
		datosUsuario.setForeground(Color.white);
		invocar.setForeground(Color.white);

		
		centroSuperior.add(datosUsuario);
		centroSuperior.add(invocar);
		centroSuperior.add(lista);
		centroInferior.add(informacion);

		opcion.getPanelCentro().add(centroSuperior);
		opcion.getPanelCentro().add(centroInferior);
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
	    opcion.getTitulo().setForeground(Color.white);
        JTextArea datos = new JTextArea(dato,15,20);           
        datos.setEditable(false);   
        datos.setFont(new Font("Serif",Font.BOLD, 20));
        JScrollPane scroll = new JScrollPane(datos);
        opcion.getPanelCentro().add(scroll);//Se añade al centro
        opcion.getPanelSuperior().setBackground(colorMenu);
        opcion.getPanelCentro().setBackground(colorMenu);
        opcion.getPanelInferior().setBackground(colorMenu);


	}
	
	//Datos del mapa 
	public void datosMapa() {
		MenuExtra infoMapa = new MenuExtra("INFORMACIÓN DEL MAPA");
		infoMapa.setResizable(false); 
		infoMapa.setSize(900,700);
		infoMapa.setLocationRelativeTo(null);

		JLabel datos = new JLabel("");
		JTextArea contexto = new JTextArea();
		contexto.setFont(new Font("Serif",Font.BOLD, 20));
		contexto.setForeground(Color.white);
		contexto.setBackground(new Color(1,1,15));
		JLabel zonaImagen = new JLabel();
		infoMapa.getPanelSuperior().setBackground(new Color(1,1,15));
		infoMapa.getPanelCentro().setBackground(new Color(1,1,15));
		infoMapa.getPanelInferior().setBackground(new Color(1,1,15));


		zonaImagen.setHorizontalAlignment(SwingConstants.CENTER);
		zonaImagen.setSize(500,500);
        
		String info =  territorio.getTitulo();
		datos = new JLabel(info);
    	
		datos.setHorizontalAlignment(SwingConstants.CENTER);
            
		contexto.setText(territorio.getTexto());
		contexto.setEditable(false);
            
		zonaImagen.setIcon(new ImageIcon(territorio.getImagenPortada().getImage().getScaledInstance(zonaImagen.getWidth(), zonaImagen.getHeight(), Image.SCALE_SMOOTH)));

		infoMapa.getPanelSuperior().add(datos);
		infoMapa.getPanelCentro().add(contexto);
		infoMapa.getPanelCentro().add(zonaImagen);   
	}
	
	//opciones de cada boton del tablero, ventana extra al hacer click en un boton con una invocacion
	public MenuExtra opcionFicha() {
		MenuExtra opcionFicha = new MenuExtra("Opciones del " + botonActual.getText());
		JPanel centro  = opcionFicha.getPanelCentro();
		JPanel centroSuperior  = new JPanel();
		JPanel centroInferior  = new JPanel();
		opcionFicha.setSize(490,400);
		opcionFicha.getPanelSuperior().setBackground(colorMenu);
		opcionFicha.getPanelCentro().setBackground(colorMenu);
		opcionFicha.getPanelInferior().setBackground(colorMenu);
		centro.add(centroSuperior);
		centro.add(centroInferior);
		atacar = new JButton("Atacar");
		moverse = new JButton("Moverse");
		habilidad = new JButton("Habilidad");
		Soldado soldadoActual = registroTablero[encontrarPosBoton()[0]][encontrarPosBoton()[1]];
		datos = new JTextArea(soldadoActual.toString());
		datos.setEditable(false);   

		atacar.setBackground(territorio.getCasilla());
		moverse.setBackground(territorio.getCasilla());
		habilidad.setBackground(territorio.getCasilla());
		datos.setFont(new Font("Serif",Font.BOLD, 15));
		datos.setForeground(Color.white);
		datos.setBackground(colorMenu);
		centroSuperior.setBackground(colorMenu);
		centroInferior.setBackground(colorMenu);
		opcionFicha.getTitulo().setForeground(Color.white);
		
		opcionFicha.setSize(250, 300);
		centroSuperior.setLayout(new GridLayout(3,1,5,5));
		centroSuperior.add(atacar);
		centroSuperior.add(moverse);
		centroSuperior.add(habilidad);
		opcionFicha.add(centro,BorderLayout.CENTER);
		atacar.addActionListener(new Listener());
		moverse.addActionListener(new Listener());
		habilidad.addActionListener(new Listener());

	
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
	public void Habilidad() {
		int[] posAtaque = encontrarPosBoton();
		Soldado soldadoAtacante = registroTablero[posAtaque[0]][posAtaque[1]];
		int[] posDefensa;
		Soldado soldadoDefensor;
		int nivelVidaNuevo;
		if(soldadoAtacante.getTipoSoldado().equals("Caballero")) {
			soldadoAtacante.morir();
			botonesTablero[posAtaque[0]][posAtaque[1]].setText("");
			registroTablero[posAtaque[0]][posAtaque[1]] = null;
		}
		for(JButton a : atacadosHabilidad) {
			botonActual = a;
			posDefensa = encontrarPosBoton();
			soldadoDefensor = registroTablero[posDefensa[0]][posDefensa[1]];
			if(soldadoDefensor.getTipoSoldado().equals("Lancero")) {
				nivelVidaNuevo = soldadoDefensor.getNivelVida()- soldadoAtacante.getNivelAtaque()*2;
			}else
				nivelVidaNuevo = soldadoDefensor.getNivelVida()- soldadoAtacante.getNivelAtaque();
			if(nivelVidaNuevo<=0) {
				soldadoDefensor.morir();
				botonesTablero[posDefensa[0]][posDefensa[1]].setText("");
				registroTablero[posDefensa[0]][posDefensa[1]]= null;
			}else
				soldadoDefensor.setNivelVida(nivelVidaNuevo);
		}
		soldadoAtacante.castigo();
		turno = !(turno);
		cambioMensaje();

	}
	//Movimiento de la ficha en los lugares pintados de rojo
	public void ataque(JButton botonAtacado) {
		int[] posAtaque = encontrarPosBoton();
		Soldado soldadoAtacante = registroTablero[posAtaque[0]][posAtaque[1]];
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
		eliminarSoldadosAtacados();
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
						botonesTablero[i][j].setBackground(colorMovimiento);
						botonesTablero[i][j].setEnabled(true);
					}
				}
			}
		}
	}
	//Pinta las casillas alrededor del boton de color rojo
	public void menuGanador(Reino reino) {
		MenuExtra menuGanador = new MenuExtra("!!!El Reino de " + reino.getReino()+" es el Ganador!!!");
		PanelPintado ganador;
		if(reino.getReino().equals(equipoAtaque.getReino())) {
			ganador = new PanelPintado("/imagenes/AtaqueWin.png");
		}else {
			ganador = new PanelPintado("/imagenes/DefensWin.png");
		}
		menuGanador.setSize(800,700);
		menuGanador.setLocationRelativeTo(null);
		menuGanador.remove(menuGanador.getPanelCentro());
		menuGanador.getTitulo().setFont(new Font("Serif",Font.BOLD, 40));
		menuGanador.getPanelSuperior().setBackground(colorMenu);
		menuGanador.getPanelInferior().setBackground(colorMenu);
		menuGanador.getTitulo().setForeground(Color.white);
		menuGanador.add(ganador,BorderLayout.CENTER);
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
	public void pintarCasillaHabilidad(int x,int y) {
		String equipo = registroTablero[x][y].getNombre().substring(registroTablero[x][y].getNombre().length()-2);
		int[] rango = registroTablero[x][y].getRangoHabilidad();
		Soldado aux;

		for(int i=x+rango[1]; i<x+rango[1]+rango[3];i++) {
			for(int j = y+rango[0]; j<y+rango[0]+rango[2];j++) {
				if(i>= 0 && i<10 && j>= 0 && j<10) {
					aux = registroTablero[i][j];
					if(aux == null ){
						botonesTablero[i][j].setBackground(colorHabilidad);
					}else if(!(aux.getNombre().substring(aux.getNombre().length()-2).equals(equipo))){
						atacadosHabilidad.add(botonesTablero[i][j]);
						botonesTablero[i][j].setBackground(colorHabilidad);
					}
					botonesTablero[i][j].setEnabled(true);	
				}
			}
		}
	}
	public void eliminarSoldadosAtacados() {
		for(int i= 0;i<atacadosHabilidad.size();i++) {
			atacadosHabilidad.remove(i);
		}
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
						botonesTablero[i][j].setBackground(colorAtaque);
						botonesTablero[i][j].setEnabled(false);
					}else if(!(aux.getNombre().substring(aux.getNombre().length()-2).equals(equipo))){
						botonesTablero[i][j].setBackground(colorAtaque);
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
			if(aux.getBackground() == colorMovimiento) {//Verifica si el boton tiene algun soldado
				movimiento(aux);
			}else if(aux.getBackground() == colorHabilidad){
				Habilidad();
			}else if(aux.getBackground() == colorAtaque){
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
				String guia = "";
				if(invocacion.equals("Arquero")) {
					guia = "Tipo de ficha : Arquero" + 
							   "\nPrecio : 5 Monedas" +
							   "\nDaño : 3 puntos"+
							   "\nVida : 5 puntos"+
							   "\nDefensa : 3 puntos"+
							   "\nHabilidad : Ataque en un rango de 2x5"+
							   "\nCoste Habilidad : 5 Monedas"+
							   "\nPenalidad por usar habilidad : Disminuye su rango de ataque";
				}else if(invocacion.equals("Caballero")) {
					guia = "Tipo de ficha : Caballero" + 
							   "\nPrecio : 8 Monedas" +
							   "\nDaño : 5 puntos"+
							   "\nVida : 15 puntos"+
							   "\nDefensa : 8 puntos"+
							   "\nHabilidad : Ataque en un rango de 5x5"+
							   "\nCoste Habilidad : 5 Monedas"+
							   "\nPenalidad por usar habilidad : La pieza muere";
				}else if(invocacion.equals("Lancero")) {
					guia = "Tipo de ficha : Lancero" + 
							   "\nPrecio : 10 Monedas" +
							   "\nDaño : 8 puntos"+
							   "\nVida : 10 puntos"+
							   "\nDefensa : 5 puntos"+
							   "\nHabilidad : Ataque en un rango de 3x4"+
							   "\nCoste Habilidad : 5 Monedas"+
							   "\nPenalidad por usar habilidad : Disminuye su rango de ataque\n y disminuye el daño en 3";
				}else {
					guia = "";
				}
				informacion.setText(guia);
				
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
				pintarCasillaHabilidad(encontrarPosBoton()[0],encontrarPosBoton()[1]);
				menuActual.dispose();
			}
		}
	}

}