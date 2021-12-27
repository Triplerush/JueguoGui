package JM1;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelPintado extends JPanel{
	  private String img;
  	
	  public PanelPintado(String img){
		  this.img = img;
	  }
	  public void paint(Graphics g) {
		  ImageIcon imagen = new ImageIcon(getClass().getResource(img));
		  g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
		  
		  setOpaque(false);
		  super.paint(g);
		  
	  }
       
  } 