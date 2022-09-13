//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Nave {
	private int x, 
				y;
	private static final int ANCHO = 60, 
							 ALTO = 20;
	private Tablero tablero;
	
	public Nave(int x, int y, Tablero tablero) {
		this.x = x;
		this.y = y;
		this.tablero = tablero;
	}
	
	public int getX() {
		return this.x+28;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void sumarX(int x) {
		this.x += x;
	}	
	
	public Rectangle2D getNave() {
	 	return new Rectangle2D.Double(this.x, this.y, ANCHO, ALTO);
	}
	
	public void pintarNave(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(this.x, this.y, ANCHO, ALTO));
		g2.drawImage(new ImageIcon("assets/Nave.png").getImage(), this.x, this.y, ANCHO, ALTO, this.tablero);
	}
}
