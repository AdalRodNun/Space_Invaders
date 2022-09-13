//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.CardLayout;

import javax.swing.JPanel;

public class Contenedor extends JPanel{
	private CardLayout cl;
	
	public Contenedor(Menu menu, Tablero tablero) {
		cl = new CardLayout();
		
		this.setLayout(cl);
		this.add(menu, "1");
		this.add(tablero, "2");
		cl.show(this, "1");
	}
	
	public void panelTablero() {
		cl.show(this, "2");
	}
}
