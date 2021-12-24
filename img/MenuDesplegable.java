package img;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class MenuDesplegable extends Menu {
    
    private JLabel titulo;
    private JComboBox list;
    private JLabel img =  new JLabel();
    private JButton botonInicio ;
    
    public MenuDesplegable(String titulo){
        super(titulo);
    }  
    
    
    
     public void contenido(String title) {
                 ///////////////////////////////////////777 
                 setDefaultCloseOperation(EXIT_ON_CLOSE);
                 setSize(400,375); 
                   
	         superior = new JPanel(new FlowLayout());
                 centro = new JPanel(new FlowLayout());
                 inferior = new JPanel(new FlowLayout());
               ///////////////////////////////////////////7
      
	         titulo = new JLabel(title);
                 titulo.setHorizontalAlignment(SwingConstants.CENTER);               
                 titulo.setFont(new Font("Serif",Font.BOLD, 20));                
                
                  JLabel anunc = new JLabel("Seleccione el Mapa a Jugar");
                  centro.add(anunc);
                  centro.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                 ////////////////////////////////////////////////
                   String [] mapas = new String[5];
                   
                   mapas[0] = "Asedio de Castle Black";
                   mapas[1] = "Tridente,Tierras en los Rios";
                   mapas[2] = "Baño del AguasNegras, Desembarco del Rey";
                   mapas[3] = "Más Allá del Muro, Tierras Inexploradas";
                   mapas[4] = "";
                
                   list = new JComboBox(mapas);
                   list.setSelectedItem("");//Para que aparezca primero ese en la lista
                   list.addActionListener(new Listener());
                   /////////////////////////////////////////////////
                  botonInicio = new JButton("IR");
                  botonInicio.setFont(new Font("Serif",Font.BOLD, 25));
                  botonInicio.setEnabled(false);
                  botonInicio.addActionListener(new Listener());
                   
                   /////////////////////////////////////////////////
                   
                   
                   superior.add(titulo);
                   centro.add(list);
                   inferior.add(botonInicio);
                             
                 
                
		   
	          add(superior,BorderLayout.NORTH);
	          add(centro,BorderLayout.CENTER);
                  add(inferior,BorderLayout.SOUTH);

        }
     
     private class Listener implements ActionListener{
         
        public void actionPerformed(ActionEvent e){
            
           String mapaSeleccionado = "";
           
           
             
            if(e.getSource() == list){
                
                 img.setSize(250,200);                 
                 ImageIcon imagenPortada ;
                 
                
                 mapaSeleccionado = (String)list.getSelectedItem();
                
                  
                  if(mapaSeleccionado.equals("")){
                      botonInicio.setEnabled(false);
                  }
                  else{
                      botonInicio.setEnabled(true);
                  }
                  
                
                
                                
                  if(mapaSeleccionado.equals("Asedio de Castle Black")){
                      
                     imagenPortada= new ImageIcon("CastleBlack.jpg");
   
                     img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));
                    
                      
                  }
                  if(mapaSeleccionado.equals("Tridente,Tierras en los Rios")){
                      
                     imagenPortada = new ImageIcon("Tridente.jpg");
                     img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));
                      
                  }
                   if(mapaSeleccionado.equals("Baño del AguasNegras, Desembarco del Rey")){
                      
                     imagenPortada = new ImageIcon("AguasNegras.jpg");
                     img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));
                      
                  }
                  if (mapaSeleccionado.equals("Más Allá del Muro, Tierras Inexploradas")) {
                        imagenPortada = new ImageIcon("MasAlladelMuro.jpg");
                     img.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(img.getWidth(), img.getHeight(), Image.SCALE_SMOOTH)));   
                  }

                 
                  centro.add(img);
         }
            
           
            
            
            
            
            if(e.getSource() == botonInicio  ) {
                
                dispose();
                
                Reino reino1 = new Reino("Peru");
                Reino reino2 = new Reino("Chile");
	
                
               	Tablero escenario = new Tablero(mapaSeleccionado,reino1,reino2);
              
            }
            
            //Añado la imagen
             
        } 
         
     }
     
       
    
}
