package JM1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;


public class Tablero extends JFrame {
	//Atributos
	static Random rd = new Random();
	private static final int ANCHO=800;
	private static final int LARGO=800;
	private MenuExtra menuActual;
	private JPanel tablero;
	private JButton[][] botonesTablero = new JButton[10][10];
	private Soldado[][] registroTablero = new Soldado[10][10];
	private JButton opcionMapa;
	private JButton ausuario;
	private JButton busuario;
	private JButton datosA;
	private JButton datosB;
	private JButton atacar;
	private JButton botonActual;
	private String territorio;
	private String invocacion = "Caballero";
	private JComboBox<String> lista;
	private Reino equipoAtaque;
	private Reino equipoDefensa;
	private boolean turno = true; //Si es verdadero es el turno del quipo atacante y si es falso es turno del defensor

	
	//Contructor del tablero con los reinos llamados para asi poder modificarlos
	public Tablero(String territorio,Reino a, Reino b) {
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
		JPanel titulo = new JPanel(new GridLayout(1,2));
		tablero = new JPanel(new GridLayout(10,10,5,5));
		JPanel usuarioa= new JPanel(new GridLayout(10,1,10,10));
		JPanel usuariob = new JPanel(new GridLayout(10,1,10,10));
		JLabel atitulo = new JLabel(equipoAtaque.getReino() + " V.S " + equipoDefensa.getReino());
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
		generarBotones();
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
        opcion.setResizable(true);
		String dato = "Ejercito: " + reino.getReino()+"\n"+
					  "Datos de los Soldados para invocar\n\n";
		for(Soldado a: reino.getSoldados()) {
			dato += a.toString() ;
		}
	     //Agrego el texto y la cantidad de filas y columnas max que puede tener, si se pasa, esta el SCROLL
        JTextArea datos = new JTextArea(dato,15,15);           
        datos.setEditable(false);   
        JScrollPane scroll = new JScrollPane(datos);
        opcion.getPanelCentro().add(scroll);//Se añade al centro
        opcion.getPanelInferior().add(new JLabel("HELLO"));
	}
	
	//Datos del mapa 
	public void datosMapa() {
	MenuExtra infoMapa = new MenuExtra("INFORMACIÓN DEL MAPA");
    infoMapa.setResizable(true); //->Si esta en falso el usuario no podra agrandar la pantalla
    infoMapa.setSize(570,270);
        
    JLabel datos = new JLabel("");
    JTextArea contexto = new JTextArea();
    JLabel zonaImagen = new JLabel();
        
    zonaImagen.setHorizontalAlignment(SwingConstants.CENTER);
    zonaImagen.setBounds(0, 0, 200, 120);
        
    if(territorio.equals("Asedio de Castle Black")){
    	String info =  territorio;
    	datos = new JLabel(info);//-->Mi label de informacion
    	
    	datos.setHorizontalAlignment(SwingConstants.CENTER);
            
    	String context = "El ejercito de Mance Rayder ataca desde el norte, el castillo tiene una guarnición de\n"
                          +  "tan solo 600 hombres , los deshonrados Guardias de la Noche. La noche se acerca y los\n "
                          + "salvajes de Rayder los superan 100 a 1. ¿Podran los Guardias de la Noche resistir el\n "
                          + "embate hasta la llegada de las tropas del Reino?";
            
        //Métodos para el TextArea
        contexto.setText(context);
        contexto.setEditable(false);
            
        //Creo la imagen
        ImageIcon imagenPortada = new ImageIcon("CastleBlack.jpg");
        zonaImagen.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(zonaImagen.getWidth(), zonaImagen.getHeight(), Image.SCALE_SMOOTH)));
            
    }
        
    if(territorio.equals("Tridente,Tierras en los Rios")) { 
	    String info =  territorio;
            
	    datos = new JLabel(info);//-->Mi label de informacion
        datos.setHorizontalAlignment(SwingConstants.CENTER);
            
            
            
        String context = "Las fuerzas rebeldes se reunieron en la ribera este de Foca Verde, los Stark y Baratheon\n"
                          +  "formaron una fuerza suficiente para derrocar a vuestro Rey Aerys II Targaryen. Comandados\n "
                          + "por Robert Baratheon, marchan al sur por el Camino Real al mayor punto de cruce del Tridente\n "
                          + "Destruye al ejercito rebelde y trae paz al reino";
            
        //Métodos para el TextArea
        contexto.setText(context);
        contexto.setEditable(false);
            
        //Creo la imagen
        ImageIcon imagenPortada = new ImageIcon("Tridente.jpg");
        zonaImagen.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(zonaImagen.getWidth(), zonaImagen.getHeight(), Image.SCALE_SMOOTH)));
          
          
    }
         
    if(territorio.equals("Baño del AguasNegras, Desembarco del Rey")){  
    	String info =  territorio;
            
	    datos = new JLabel(info);//-->Mi label de informacion
        datos.setHorizontalAlignment(SwingConstants.CENTER);
            
        String context = " El ejército de Stannis marcha hacia Desembarco del Rey. Aumentado con las fuerzas de las\n "
                          + "Tierras de Tormentas, el rey Stannis tiene a su mando casi veinte mil hombres  . Para intentar\n"
                          + "hacerle frente, Tyrion Lannister triplica el número de efectivos de los capas doradas de la\n"
                          + "Guardia de la Ciudad de Desembarco del Rey. Solo un ejercito quedará en pie";                       
                          
        //Métodos para el TextArea
        contexto.setText(context);
        contexto.setEditable(false);
            
        //Creo la imagen
        ImageIcon imagenPortada = new ImageIcon("AguasNegras.jpg");
        zonaImagen.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(zonaImagen.getWidth(), zonaImagen.getHeight(), Image.SCALE_SMOOTH)));
          
          
    }
          
    if(territorio.equals("Más Allá del Muro, Tierras Inexploradas")){
	    String info =  territorio; 
	    
	    datos = new JLabel(info);//-->Mi label de informacion
        datos.setHorizontalAlignment(SwingConstants.CENTER);
            
        String context = "Se anuncia, que los Caminates Blancos asaltaran las puertas del Reino ,los hombres liderados por\n "
                          + "Jon Snow se alzan en armas y marchan en contra de este nuevo enemigo , con un nuevo territorio por \n"
                          + "explorar y con pocos recursos los valientes guerreros se dirigen a una muerte segura, no obstante\n"
                          + "una gran sorpresa se prepara en las tierras del Reino que se dice pondra fin a este nueva amenaza";                       
                          
        //Métodos para el TextArea
        contexto.setText(context);
        contexto.setEditable(false);
            
        //Creo la imagen
        ImageIcon imagenPortada = new ImageIcon("MasAlladelMuro.jpg");
        zonaImagen.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(zonaImagen.getWidth(), zonaImagen.getHeight(), Image.SCALE_SMOOTH)));
          
          
    }  
    
    //Añado datos al  area Norte de infoMapa
    infoMapa.getPanelSuperior().add(datos);
    //La inforacion del mapa la añado aqui
    infoMapa.getPanelCentro().add(contexto);
    //Añado la imagen abajo
    infoMapa.getPanelInferior().add(zonaImagen);   
    
	}
	
	//opciones de cada boton del tablero, ventana extra al hacer click en un boton con una invocacion
	public MenuExtra opcionFicha(String ficha) {
		MenuExtra opcionFicha = new MenuExtra("Opciones del : " + ficha);
		opcionFicha.setSize(250, 300);
		JPanel centro  = opcionFicha.getPanelCentro();
		atacar = new JButton("Atacar");
		JButton habilidad1 = new JButton("Habilidad 1");
		JButton habilidad2 = new JButton("Habilidad 2");;
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
		if(turno){
			if(invocacion.equals("Caballero")) {
				soldado = equipoAtaque.generarCaballero(invocacion+" - E1");
			}else if(invocacion.equals("Lancero")) {
				soldado = equipoAtaque.generarLancero(invocacion+" - E1");
			}else {
				soldado = equipoAtaque.generarArquero(invocacion+" - E1");
			}
		}else {
			if(invocacion.equals("Caballero")) {
				soldado = equipoDefensa.generarCaballero(invocacion+" - E2");
			}else if(invocacion.equals("Lancero")) {
				soldado = equipoDefensa.generarLancero(invocacion+" - E2");
			}else {
				soldado = equipoDefensa.generarArquero(invocacion+" - E2");
			}
		}
		registroTablero[temp[0]][temp[1]] = soldado ;
		turno = !(turno);
	}
	//Pinta las casillas alrededor del boton de color rojo
	public void movimiento(JButton botonMov) {
		botonMov.setText(botonActual.getText());		
		botonMov.setBackground(botonActual.getBackground());
		botonMov.setEnabled(true);;
		botonActual.setText("");
		botonActual.setBackground(Color.gray);
		botonActual.setEnabled(false);;
		int[] posAnterior = encontrarPosBoton();
		botonActual = botonMov;
		int[] posDespues = encontrarPosBoton();
		registroTablero[posDespues[0]][posDespues[1]] =  registroTablero[posAnterior[0]][posAnterior[1]];
		registroTablero[posAnterior[0]][posAnterior[1]] = null;
		turno = !(turno);

	}
	//Cuando se invoque a un soldado este metodo pinta de color la casilla dependiendo el soldado
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
	public void anularTablero() {
		for (int i = 0;i<10;i++) {
			for (int j = 0;j<5;j++) {
				if(registroTablero[i][j] == null) {
					botonesTablero[i][j].setEnabled(turno);
					botonesTablero[i][j].setBackground(Color.gray);
				}else {
					String nombre = botonesTablero[i][j].getText().substring(0,botonesTablero[i][j].getText().length()-5);
					if(nombre.equals("Caballero")) {
						botonesTablero[i][j].setBackground(Color.cyan);
					}else if(nombre.equals("Lancero")) {
						botonesTablero[i][j].setBackground(Color.RED);
					}else {
						botonesTablero[i][j].setBackground(Color.ORANGE);
					}
				}
				if(registroTablero[i][j+5] == null){
					botonesTablero[i][j+5].setEnabled(!(turno));
					botonesTablero[i][j+5].setBackground(Color.gray);
				}else {
					String nombre = botonesTablero[i][j+5].getText().substring(0,botonesTablero[i][j+5].getText().length()-5);
					if(nombre.equals("Caballero")) {
						botonesTablero[i][j+5].setBackground(Color.cyan);
					}else if(nombre.equals("Lancero")) {
						botonesTablero[i][j+5].setBackground(Color.RED);
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
			}else if(!(aux.getText().equals(""))){
				botonActual = aux;
				menuActual = opcionFicha(invocacion);
			}else{
				botonActual = aux;
				nombrarCasilla(aux);
				pintarCasilla(aux);
				generarSoldado();
			}
			anularTablero();
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
			}else if(e.getSource() == atacar){
				pintarCasillaAlrededor(encontrarPosBoton()[0],encontrarPosBoton()[1]);
				menuActual.dispose();
			}
		}
	}
}