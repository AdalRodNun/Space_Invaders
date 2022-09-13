//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import javax.swing.JFrame;

public class Ventana extends JFrame {
	public Ventana() {
		super("Space Invaders");
		Tablero tablero = new Tablero();
		Menu menu = new Menu();
		Contenedor contenedor = new Contenedor(menu, tablero);
		this.add(contenedor);
		this.addKeyListener(new Teclado(tablero, contenedor));
		this.pack();
		this.setLocationRelativeTo(null); //Inicia la ventana en el centro de la pantalla
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new Ventana();
	}
}
