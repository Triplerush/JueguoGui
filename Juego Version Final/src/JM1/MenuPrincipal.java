package JM1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public class MenuPrincipal extends Menu {
   
    private static JLabel zonaImagen;
    PanelPintado fondo = new PanelPintado("/imagenes/GOT.png");

    
    public MenuPrincipal(String titulo){
    	super(titulo);
        this.setLocationRelativeTo(null);   
        

    } 

    public void contenido(String title) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setSize(900, 800);
    	
		superior = new JPanel(new FlowLayout());
        centro = new JPanel(new FlowLayout());
        inferior = new JPanel(new FlowLayout());
                
        superior.setBackground(new Color(1, 1, 5));
        centro.setBackground(new Color(1, 1, 5));
        inferior.setBackground(new Color(1, 1, 5));

        JLabel nombreJuego = new JLabel("SIMULADOR DE BATALLA");
        nombreJuego.setForeground(new Color(92, 92, 255));
        nombreJuego.setFont(new Font("Serif",Font.BOLD, 40));
        superior.add(nombreJuego);
        
        //Imagen que agregare al Centro
        zonaImagen = new JLabel("");
        zonaImagen.setBounds(5, 5, 700, 600);
        zonaImagen.setHorizontalAlignment(SwingConstants.CENTER );
                
        ImageIcon imagenPortada = new ImageIcon("GOT.png");
        zonaImagen.setIcon(new ImageIcon(imagenPortada.getImage().getScaledInstance(zonaImagen.getWidth(), zonaImagen.getHeight(), Image.SCALE_SMOOTH)));
                
        getPanelCentro().add(zonaImagen);          
                 
        JButton inicio = new JButton("JUGAR");
                 
        inicio.setFont(new Font("Serif",Font.ITALIC, 30));
        inicio.addActionListener(new Listener());
        inicio.setBackground(new Color(92, 92, 255));
                           
        getPanelInferior().add(inicio);//Agrego el boton

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