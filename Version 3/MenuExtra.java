package JM1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MenuExtra extends JFrame {
	private static final int ANCHO=250;
	private static final int LARGO=300;
	private JPanel superior;
	private JPanel centro;
	
	public MenuExtra(String titulo) {
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
	public void contenido(String title) {
		superior = new JPanel(new GridLayout(2,1));
		JLabel titulo = new JLabel(title);
		centro = new JPanel(new FlowLayout());

		superior.add(titulo);
		add(superior,BorderLayout.NORTH);
		add(centro,BorderLayout.CENTER);

	}
}
		