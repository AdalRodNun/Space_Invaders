//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Jugador {
	private int score,
				vidas;
	
	public Jugador() {
		this.score = 0;
		this.vidas = 3;
	}

	public int getScore() {
		return this.score;
	}
	
	public void resetScore() {
		this.score = 0;
	}
	
	public void incrementScore() {
		this.score += 20;
	}
	
	public void quitarVida() {
		this.vidas -= 1;
	}
	
	public int getVidas() {
		return this.vidas;
	}
	
	public void setVidas() {
		this.vidas = 3;
	}
	public void pintarScore(Graphics g) {
		Font font = new Font("GAME OVER", Font.ITALIC, 20);
		g.setFont(font);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(this.score), 500, 50);
	}
}
