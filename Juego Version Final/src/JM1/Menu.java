package JM1;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.*;

public abstract class Menu extends JFrame {
    private int ancho=350;
    private int largo=470;
    protected JPanel superior;
    protected JPanel centro;
    protected JPanel inferior;      


    public Menu(String titulo) {
    	setTitle(titulo);
    	setSize(ancho,largo);
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
