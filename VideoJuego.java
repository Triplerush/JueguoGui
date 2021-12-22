package JM1;
import java.util.*;
import javax.swing.*;

public class VideoJuego {
	public static void main(String[] args) {
		Reino reino1 = new Reino("Peru");
		Reino reino2 = new Reino("Chile");
<<<<<<< HEAD
=======
		reino2.generarSoldados(5);
>>>>>>> 8acebc32cd8c479892f14cce034cbc183aa0495f
		Tablero escenario = new Tablero("Desierto",reino1,reino2);
	}

}