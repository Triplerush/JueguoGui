package JM1;
import java.util.*;
import javax.swing.*;

public class VideoJuego {
	public static void main(String[] args) {
		Reino reino1 = new Reino("Peru");
		Reino reino2 = new Reino("Chile");
		Tablero escenario = new Tablero("Desierto",reino1,reino2);
	}

}