//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Disparo {
	private int x,
				y,
				tipo; //0 alien, 1 nave
	private static final int ANCHO = 4, 
							 LARGO = 15;
	
	public Disparo(int x, int y, int tipo) {
		this.x = x;
		this.y = y;
		this.tipo = tipo;
	}
	
	public void moverDisparo() {
		
		if(this.tipo == 0) {
			this.y++;
		} else if (this.tipo == 1){
			this.y -= 2;
		}
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getTipo() {
		return this.tipo;
	}
	
	public boolean colision(Rectangle2D r) {
		return new Rectangle2D.Double(this.x, this.y, ANCHO, LARGO).intersects(r);
	}
	
	public void pintarDisparo(Graphics2D g2) {
		g2.setColor(Color.WHITE);
		g2.fill(new Rectangle2D.Double(this.x, this.y, ANCHO, LARGO));
	}
}
