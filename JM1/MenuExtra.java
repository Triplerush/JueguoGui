package JM1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MenuExtra extends Menu {
    private JLabel titulo;

	
    public MenuExtra(String titulo){
        super(titulo);
    } 
    public void contenido(String title) {
    	superior = new JPanel(new GridLayout(2,1));
    	centro = new JPanel(new FlowLayout());
    	inferior = new JPanel(new FlowLayout());
    	
    	//EL TITULO "Datos del Mapa" 
    	titulo = new JLabel(title);
    	titulo.setHorizontalAlignment(SwingConstants.CENTER);
    	                
    	superior.add(titulo, BorderLayout.CENTER);
    	add(superior,BorderLayout.NORTH);
    	add(centro,BorderLayout.CENTER);
    	add(inferior,BorderLayout.SOUTH);

    }   
    
    public JLabel getTitulo(){
    	 return titulo;
    }
    	    
}
		