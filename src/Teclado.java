//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener{
	private Tablero tablero;
	private Contenedor contenedor;
	
	public Teclado(Tablero tablero, Contenedor contenedor) {
		this.tablero = tablero;
		this.contenedor = contenedor;
	}
	
	public synchronized void keyPressed(KeyEvent evt) {
		if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.tablero.moverNaveDer();
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_LEFT) {
			this.tablero.moverNaveIzq();
		}
	}

	public synchronized void keyReleased(KeyEvent evt) {
		if(evt.getKeyCode() == KeyEvent.VK_SPACE) {
			this.tablero.crearDisparoNave();
		}
		
		if(evt.getKeyCode() == KeyEvent.VK_ENTER) { //Inicia el juego y cambia la pantalla del menu al tablero
			this.tablero.iniciarJuego();
			this.contenedor.panelTablero();
		}
	}

	public void keyTyped(KeyEvent evt) {
	}
}
