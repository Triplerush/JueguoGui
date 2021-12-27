package JM1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class MenuDesplegable extends Menu {
    
    private JLabel titulo;
    private JComboBox list;
    private JLabel img =  new JLabel();
    private JButton botonInicio;
    private String mapaSeleccionado;
    private Mapa mapa;
    
    public MenuDesplegable(String titulo){
        super(titulo);
    }  
    
    public void contenido(String title) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900,800); 
        this.setLocationRelativeTo(null);    

                   
	    superior = new JPanel(new GridLayout(3,1));
        centro = new JPanel(new FlowLayout());
        inferior = new JPanel(new FlowLayout());
        
        superior.setBackground(new Color(63, 61, 57));
        centro.setBackground(new Color(63, 61, 57));
        inferior.setBackground(new Color(63, 61, 57));
              
        titulo = new JLabel(title);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);               
        titulo.setFont(new Font("Serif",Font.BOLD, 20));    
        titulo.setForeground(Color.white);
                
        JLabel anunc = new JLabel("Seleccione el Mapa a Jugar");
        anunc.setForeground(Color.white);
        
        centro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                 
        String [] mapas = new String[5];
                   
        mapas[0] = "Asedio de Castle Black";
        mapas[1] = "Tridente,Tierras en los Rios";
        mapas[2] = "Baño del AguasNegras, Desembarco del Rey";
        mapas[3] = "Más Allá del Muro, Tierras Inexploradas";
        mapas[4] = "";
                
        list = new JComboBox(mapas);
        list.setSelectedItem("");//Para que aparezca primero ese en la lista
        list.addActionListener(new Listener());
            
        botonInicio = new JButton("IR");
        botonInicio.setFont(new Font("Serif",Font.BOLD, 25));
        botonInicio.setEnabled(false);
        botonInicio.addActionListener(new Listener());
                   
        superior.add(titulo);
        superior.add(anunc);
        superior.add(list);
        inferior.add(botonInicio);
                             
	    add(superior,BorderLayout.NORTH);
	    add(centro,BorderLayout.CENTER);
        add(inferior,BorderLayout.SOUTH);

    }
     
    private class Listener implements ActionListener{
         
        public void actionPerformed(ActionEvent e){
        	
           if(e.getSource() == list){
        	   img.setSize(900,626);  
               mapaSeleccionado = (String)list.getSelectedItem();
        	   
               String contexto;
        	   ImageIcon imagenPortada ;
     
               if(mapaSeleccionado.equals(""))
            	   botonInicio.setEnabled(false);
               else
                   botonInicio.setEnabled(true);
                          
               if(mapaSeleccionado.equals("Asedio de Castle Black")){
            	   
            	  contexto="El ejercito de Mance Rayder ataca desde el norte, el castillo tiene una guarnición de\n"
                          +  "tan solo 600 hombres , los deshonrados Guardias de la Noche. La noche se acerca y los\n "
                          + "salvajes de Rayder los superan 100 a 1. ¿Podran los Guardias de la Noche resistir el\n "
                          + "embate hasta la llegada de las tropas del Reino?";
                  
            	  imagenPortada= new ImageIcon("CastleBlack.jpg");
                  img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));
                  
            	  mapa = new Mapa(mapaSeleccionado,contexto,imagenPortada,new Color(119,52,0),new Color(211,170,93),"/imagenes/BaseAtaqueCastillo.png","/imagenes/BaseDefensaCastillo.png");
       
               }else if(mapaSeleccionado.equals("Tridente,Tierras en los Rios")){
            	   
            	   contexto="Las fuerzas rebeldes se reunieron en la ribera este de Foca Verde, los Stark y Baratheon\n"
                           +  "formaron una fuerza suficiente para derrocar a vuestro Rey Aerys II Targaryen. Comandados\n "
                           + "por Robert Baratheon, marchan al sur por el Camino Real al mayor punto de cruce del Tridente\n "
                           + "Destruye al ejercito rebelde y trae paz al reino"; 
            	   imagenPortada = new ImageIcon("Tridente.jpg");
            	   img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));
             	   mapa = new Mapa(mapaSeleccionado,contexto,imagenPortada,new Color(101,104,106),new Color(231,235,236),"/imagenes/BaseAtaqueTridente.png","/imagenes/BaseDefensaTridente.png");
   
               }else if(mapaSeleccionado.equals("Baño del AguasNegras, Desembarco del Rey")){
            	   
            	   contexto=" El ejército de Stannis marcha hacia Desembarco del Rey. Aumentado con las fuerzas de las\n "
                           + "Tierras de Tormentas, el rey Stannis tiene a su mando casi veinte mil hombres  . Para intentar\n"
                           + "hacerle frente, Tyrion Lannister triplica el número de efectivos de los capas doradas de la\n"
                           + "Guardia de la Ciudad de Desembarco del Rey. Solo un ejercito quedará en pie";   
            	   imagenPortada = new ImageIcon("AguasNegras.jpg");
            	   img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));
             	   mapa = new Mapa(mapaSeleccionado,contexto,imagenPortada,new Color(66,106,3),new Color(171,234,116),"/imagenes/BaseAtaqueAguas.png","/imagenes/BaseDefensaAguas.png");
  
               }else if (mapaSeleccionado.equals("Más Allá del Muro, Tierras Inexploradas")) {
            	   
            	   contexto="Se anuncia, que los Caminates Blancos asaltaran las puertas del Reino ,los hombres liderados por\n "
                           + "Jon Snow se alzan en armas y marchan en contra de este nuevo enemigo , con un nuevo territorio por \n"
                           + "explorar y con pocos recursos los valientes guerreros se dirigen a una muerte segura, no obstante\n"
                           + "una gran sorpresa se prepara en las tierras del Reino que se dice pondra fin a este nueva amenaza";       
            	   imagenPortada = new ImageIcon("MasAlladelMuro.jpg");
                   img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));   
                   mapa = new Mapa(mapaSeleccionado,contexto,imagenPortada,new Color(29,92,185),new Color(165,232,236),"/imagenes/BaseAtaqueMuro.png","/imagenes/BaseDefensaMuro.png");

               }
               centro.add(img);
         }
           
           if(e.getSource() == botonInicio  ) {
                dispose();
                
                Reino reino1 = new Reino("Casa Lanister");
                Reino reino2 = new Reino("Casa Stark");
	
               	Tablero escenario = new Tablero(mapa,reino1,reino2);
              
            }    
        } 
 
     }
}
