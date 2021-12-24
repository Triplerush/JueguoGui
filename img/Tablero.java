package img;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class Tablero extends JFrame{
    static Random rd = new Random();
	private static final int ANCHO=800;
	private static final int LARGO=800;
       ////////////////////////
	private JButton opcionMapa;
	private JButton botonA;
	private JButton botonB;
	private JButton datosA;
	private JButton datosB;
       /////////////////////////
	private Soldado [][] registro = new Soldado[10][10];
	private String territorio;
	private String invocacion = "";
	private JComboBox<String> lista;
       ///////////////////////////
       //Estos son los reinos que se enfrentan
	private Reino ataque;
	private Reino defensa;

	
	public Tablero(String territorio,Reino a, Reino b){
		this.setTerritorio(territorio);
		ataque = a;
		defensa = b;
		setTitle("Juego Batalla");
		setSize(ANCHO,LARGO);
		setLayout(new BorderLayout()); //El Layout sera uno de tipo BOrder : DE TIPOS CARDINALES
		contenido();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
   
	}
       
       
       
	public void contenido() {
               ///////////////  HAY 4 JPanel ////////////////////////////
		JPanel titulo = new JPanel(new GridLayout(1,2)); //IRA EN LA  PARTE NORTE
		JPanel tablero = new JPanel(new GridLayout(10,10)); //IRA EN LA PARTE CENTRAL
		JPanel usuarioA= new JPanel(new GridLayout(10,1)); //IRA EN LA PARTE "IZQUIERDA"
		JPanel usuarioB = new JPanel(new GridLayout(10,1)); //IRA EN LA PARTE "DERECHA"
                ///////////////////////////////
               
		JLabel tituloJuego = new JLabel("JUEGO DE TRONOS"); //Especifico el título
               tituloJuego.setBounds(0, 0, 50, 50);
               tituloJuego.setHorizontalAlignment(SwingConstants.CENTER); //La alineación que tendra en su casilla
               tituloJuego.setForeground(Color.BLUE); //Color de la letra
               ///--> Creo mi imagen que añadire al JLabel "tituloJuego"
               ImageIcon imagenPortada = new ImageIcon("GOT.jpg");
               tituloJuego.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(tituloJuego.getWidth(), tituloJuego.getHeight(), Image.SCALE_SMOOTH)));
               //titulo.add(tituloJuego)->LO añado a JPanel titulo
               
               
               
              //Serán añadidos a sus respectivos JPanel "USUARIOS"
               botonA = new JButton("Usuario A"); 
               botonB = new JButton("Usuario B");
               ///////////////////////////////
               //Serán añadidos a sus respectivos JPanel "USUARIOS"
               datosA = new JButton("Datos A");
		datosB = new JButton("Datos B");
               ///////////////////////////////
               opcionMapa = new JButton("Mapa");
               
               //Aqui agrego los botones que seran las posiciones en el campo de batalla
               for (int i = 1;i<=100;i++) {
			JButton boton = new JButton();
			tablero.add(boton);
			boton.addActionListener(new ListenerJuego());
		}
               
            
		titulo.add(tituloJuego); 
               titulo.add(opcionMapa);
               
               usuarioA.add(botonA);
		usuarioB.add(botonB);
               
               usuarioA.add(datosA);
		usuarioB.add(datosB);
	
               
               ////AÑADO LOS 4  PANEL CREADOS ANTERIORMENTE
		add(titulo,BorderLayout.NORTH);
		add(tablero,BorderLayout.CENTER);
		add(usuarioA,BorderLayout.WEST);
	        add(usuarioB,BorderLayout.EAST);
               
               //// Si elige opcion Mapa mostrará los datos del Mapa
               opcionMapa.addActionListener(new ListenerJuego());
               //// Si eleige el botonA o el botonB muestra las opciones del Usuario (como la tienda)
               botonA.addActionListener(new ListenerJuego());
               botonB.addActionListener(new ListenerJuego());
               
		//Si eligen el boton datosA o datosB te muestra los datos de ese reino
		datosA.addActionListener(new ListenerJuego());
		datosB.addActionListener(new ListenerJuego());
         
	}
       
       private class ListenerJuego implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JButton aux = (JButton)e.getSource();
			if(e.getSource() == botonB){
				opcionUsuario();
			}else if(e.getSource() == botonA){
				opcionUsuario();
			}else if(e.getSource() == opcionMapa){
				datosMapa();
			}else if(e.getSource() == datosA) {
				datosUsuario(ataque);
			}else if(e.getSource() == datosB){
				datosUsuario(defensa);
			}else if(aux.getText().equals(invocacion)) {
				aux.setText("");
			}else{
				aux.setText(invocacion);
			}	
		}
	}
       
       
       public void opcionUsuario() {
		
               ///////////////////////////////////////////////////////////////////////////////////
		MenuExtra opcion = new MenuExtra("Opciones del jugador");
               opcion.getPanelSuperior().setLayout(new GridLayout(2,1)); ///LO ACTUALIZO AGRIDLAYOUT
                            
		String datos = "1.-Puntos para comprar: " + rd.nextInt(15); //La cantidad de puntos que tendras para generar Soldados
		JLabel datosUsuario = new JLabel(datos);
              
               datosUsuario.setHorizontalAlignment(SwingConstants.CENTER);
               
               opcion.getPanelSuperior().add(datosUsuario); 
               //////////////////////////////////////////////////////////////////////////////////////////
               
               JLabel invocar = new JLabel("Soldado para invocar");
               String[] invocaciones = {"Soldado", "Mago", "Guerrero"}; //Tipos de soldados que hay
		lista = new JComboBox<String>(invocaciones);
               
               opcion.getPanelCentro().add(invocar);
               opcion.getPanelCentro().add(lista);
               	
		lista.addActionListener(new Listener()); //Si selecciona un tipo de Soldado
	}
       
       
       //Muestra los datos del mapa , como el tipo de territorio
       public void datosMapa() {
		MenuExtra infoMapa = new MenuExtra("INFORMACIÓN DEL MAPA");
               infoMapa.setResizable(false); //->Si esta en falso el usuario no podra agrandar la pantalla
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
       
       
       //Muetra todos los datos del reino, todos los soldados que crearon 
       public void datosUsuario(Reino reino) {
    	   MenuExtra opcion = new MenuExtra("Datos del jugador");
           opcion.setResizable(true);
         
           String dato = "EJERCITO : " + reino.getReino()+"\n"+
					  "Datos de los Soldados :  \n\n";
           for(Soldado a: reino.getSoldados()) {
        	   dato += a.toString() ;
           }
           
           //Agrego el texto y la cantidad de filas y columnas max que puede tener, si se pasa, esta el SCROLL
           JTextArea datos = new JTextArea(dato,10,15);           
           datos.setEditable(false);   
           JScrollPane scroll = new JScrollPane(datos);
           opcion.getPanelCentro().add(scroll);//Se añade al centro
           opcion.getPanelInferior().add(new JLabel("HELLO"));
           
           
                             
	}
       
       
       private class Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == lista) {
				invocacion = (String)lista.getSelectedItem();		
			}
		}
	}
   
 
       
	public JButton getMapa() {
		return  opcionMapa;
	}
       

       
    public String getTerritorio() {
		return territorio;
	}
	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}
}
