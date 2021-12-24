package JM1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public abstract class Menu extends JFrame {
    private static final int ANCHO=100;
    private static final int LARGO=300;
    protected JPanel superior;
    protected JPanel centro;
    protected JPanel inferior;      


    public Menu(String titulo) {
    	setTitle(titulo);
    	setSize(ANCHO,LARGO);
    	setLayout(new BorderLayout());
    	contenido(titulo);
    	setVisible(true);
    }
    public JPanel getPanelSuperior() {
    	return superior;
    }
    public JPanel getPanelCentro() {
    	return centro;
    }
    public JPanel getPanelInferior(){
        return inferior;
    }
    
    public abstract void contenido(String title);
	}
