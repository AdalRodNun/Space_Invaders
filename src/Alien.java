//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;

public class Alien {
	private int x,
				y,
				contador = 0,
				numMovimientos;
	private boolean movimiento;
	private static final int ANCHO = 25, //11 
							 ALTO = 18;  //8
	private Tablero tablero;
	private Image imagen,
				  sprite1,
				  sprite2;
	
	public Alien(int x, int y, Tablero tablero) {
		this.x = x;
		this.y = y;
		this.tablero = tablero;
		this.sprite1 = new ImageIcon("assets/Alien1.png").getImage();
		this.sprite2 = new ImageIcon("assets/Alien2.png").getImage();
		this.imagen = this.sprite1;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void moverAlien() {
		
		if(this.movimiento == false) {
			this.x += 5;
			this.numMovimientos += 1;
			cambiarSprite();
		} else {
			this.x -= 5;
			this.numMovimientos -= 1;
			cambiarSprite();
		}
		
		if(this.numMovimientos > 8 ) {
			this.y += 20;
			this.x -= 5;
			this.movimiento = true;
			cambiarSprite();
		} else if(this.numMovimientos < -7) {
			this.y += 20;
			this.x += 5;
			this.movimiento = false;
			cambiarSprite();
		}
	}
	
	private void cambiarSprite() {
		this.contador++;

		if((this.contador)%2 == 0) {
			this.imagen = this.sprite1;
		} else {
			this.imagen = this.sprite2;
		}
	}
	
	public Rectangle2D getAlien() {
	 	return new Rectangle2D.Double(this.x, this.y, ANCHO, ALTO);
	}
	
	public void pintarAlien(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(this.x, this.y, ANCHO, ALTO));
		g2.drawImage(this.imagen, this.x, this.y, ANCHO, ALTO, this.tablero);
	}
}
