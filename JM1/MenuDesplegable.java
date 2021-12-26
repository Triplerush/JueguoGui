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
        setSize(400,375); 
                   
	    superior = new JPanel(new FlowLayout());
        centro = new JPanel(new FlowLayout());
        inferior = new JPanel(new FlowLayout());
              
        titulo = new JLabel(title);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);               
        titulo.setFont(new Font("Serif",Font.BOLD, 20));                
                
        JLabel anunc = new JLabel("Seleccione el Mapa a Jugar");
        centro.add(anunc);
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
        centro.add(list);
        inferior.add(botonInicio);
                             
	    add(superior,BorderLayout.NORTH);
	    add(centro,BorderLayout.CENTER);
        add(inferior,BorderLayout.SOUTH);

    }
     
    private class Listener implements ActionListener{
         
        public void actionPerformed(ActionEvent e){
        	
           if(e.getSource() == list){
        	   img.setSize(250,200);  
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
            	  mapa = new Mapa(mapaSeleccionado,contexto,imagenPortada,Color.black,Color.gray,Color.lightGray);
       
               }else if(mapaSeleccionado.equals("Tridente,Tierras en los Rios")){
            	   
            	   contexto="Las fuerzas rebeldes se reunieron en la ribera este de Foca Verde, los Stark y Baratheon\n"
                           +  "formaron una fuerza suficiente para derrocar a vuestro Rey Aerys II Targaryen. Comandados\n "
                           + "por Robert Baratheon, marchan al sur por el Camino Real al mayor punto de cruce del Tridente\n "
                           + "Destruye al ejercito rebelde y trae paz al reino"; 
            	   imagenPortada = new ImageIcon("Tridente.jpg");
            	   img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));
             	   mapa = new Mapa(mapaSeleccionado,contexto,imagenPortada,Color.blue,Color.red,Color.darkGray);
   
               }else if(mapaSeleccionado.equals("Baño del AguasNegras, Desembarco del Rey")){
            	   
            	   contexto=" El ejército de Stannis marcha hacia Desembarco del Rey. Aumentado con las fuerzas de las\n "
                           + "Tierras de Tormentas, el rey Stannis tiene a su mando casi veinte mil hombres  . Para intentar\n"
                           + "hacerle frente, Tyrion Lannister triplica el número de efectivos de los capas doradas de la\n"
                           + "Guardia de la Ciudad de Desembarco del Rey. Solo un ejercito quedará en pie";   
            	   imagenPortada = new ImageIcon("AguasNegras.jpg");
            	   img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));
             	   mapa = new Mapa(mapaSeleccionado,contexto,imagenPortada,Color.black,Color.gray,Color.darkGray);
  
               }else if (mapaSeleccionado.equals("Más Allá del Muro, Tierras Inexploradas")) {
            	   
            	   contexto="Se anuncia, que los Caminates Blancos asaltaran las puertas del Reino ,los hombres liderados por\n "
                           + "Jon Snow se alzan en armas y marchan en contra de este nuevo enemigo , con un nuevo territorio por \n"
                           + "explorar y con pocos recursos los valientes guerreros se dirigen a una muerte segura, no obstante\n"
                           + "una gran sorpresa se prepara en las tierras del Reino que se dice pondra fin a este nueva amenaza";       
            	   imagenPortada = new ImageIcon("MasAlladelMuro.jpg");
                   img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));   
                   mapa = new Mapa(mapaSeleccionado,contexto,imagenPortada,Color.white,Color.WHITE,Color.cyan);

               }
               centro.add(img);
         }
           
           if(e.getSource() == botonInicio  ) {
                dispose();
                
                Reino reino1 = new Reino("Peru");
                Reino reino2 = new Reino("Chile");
	
               	Tablero escenario = new Tablero(mapa,reino1,reino2);
              
            }    
        } 
 
     }
}
