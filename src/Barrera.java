//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Barrera {
	private int x, 
				y;
	private static final int ANCHO = 15, 
							 ALTO = 15;
	
	public Barrera(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Rectangle2D getBarrera() {
	 	return new Rectangle2D.Double(this.x, this.y, ANCHO, ALTO);
	}
	
	public void pintarBarrera(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fill(new Rectangle2D.Double(this.x, this.y, ANCHO, ALTO));
	}
}
