//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Explosion {
	private int alienX,
				alienY,
				cont;
	private Tablero tablero;
	
	public Explosion(int alienX, int alienY, Tablero tablero) {
		this.alienX = alienX;
		this.alienY = alienY;
		this.tablero = tablero;
	}
	
	public void pintarExplosion(Graphics g) {
		g.drawImage(new ImageIcon("assets/Explosion.png").getImage(), this.alienX-5, this.alienY-5, 35, 25, this.tablero);
	}
	
	public boolean quitarExplosion() {
		if(cont++ == 300) {
			return true;
		}
		return false;
	}
}
