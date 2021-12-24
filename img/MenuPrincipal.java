package img;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class MenuPrincipal extends Menu {
    
    JFrame este = this;
    
    
    private static JLabel zonaImagen;
    
    public MenuPrincipal(String titulo){
        super(titulo);
    } 
    
   
    
    
      public void contenido(String title) {
          
                setSize(540, 500);
                
		superior = new JPanel(new FlowLayout());
                centro = new JPanel(new FlowLayout());
                inferior = new JPanel(new FlowLayout());
                
                /////////////////////////////////////////////////////////////////////
                JLabel nombreJuego = new JLabel("SIMULADOR DE BATALLA");
                       nombreJuego.setFont(new Font("Serif",Font.BOLD, 40));
                 superior.add(nombreJuego);
                //////////////////////////////////////////////////////////////////////
                 //Imagen que agregare al Centro
                zonaImagen = new JLabel("");
                zonaImagen.setBounds(5, 5, 400, 400);
                zonaImagen.setHorizontalAlignment(SwingConstants.CENTER );
                
                ImageIcon imagenPortada = new ImageIcon("GOT.jpg");
                zonaImagen.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(zonaImagen.getWidth(), zonaImagen.getHeight(), Image.SCALE_SMOOTH)));
                
                 getPanelCentro().add(zonaImagen);          
                 ///////////////////////////////////////////////////////////////////////
                 
                JButton inicio = new JButton("JUGAR");
                 
                inicio.setFont(new Font("Serif",Font.ITALIC, 30));
                inicio.addActionListener(new Listener());
                           
                getPanelInferior().add(inicio);//Agrego el boton
                
                ///////////////////////////////////////////////////////////////////////
             
	
                
		
		add(superior,BorderLayout.NORTH);
		add(centro,BorderLayout.CENTER);
                add(inferior,BorderLayout.SOUTH);

        }
   
      
      private class Listener implements ActionListener{
          
          
          
          public void actionPerformed(ActionEvent e ){
              
              
              if(e.getActionCommand().equals("JUGAR")){
              
                 dispose(); //Para que se cierre al apretar ese boton
              
                 MenuDesplegable mMapas = new MenuDesplegable("JUEGO DE TRONOS");
                
                   
              }
              
            
      
          }
          
      }
      
      
      
}