//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tablero extends JPanel implements Runnable {
	private Nave nave;
	private static final int ANCHO = 550,
							 ALTO = 750;
	private int contAliens = 0,
				contFase,
				velocidad,
				fase; // 1 = juego, 2 = pausa, 3 = game over
	private boolean bandera,
					gameOver;
	private ArrayList<Disparo> disparos;
	private ArrayList<Alien> aliens; 
	private ArrayList<Barrera> barreras;
	private ArrayList<Explosion> explosiones;
	private Jugador jugador; 
	
	public Tablero() {
		super();
		this.setPreferredSize(new Dimension(ANCHO, ALTO));
		setBackground(Color.BLACK);
		this.fase = 0;
		this.jugador = new Jugador();
		this.barreras = new ArrayList<>();
		this.disparos = new ArrayList<>();
		this.aliens = new ArrayList<>();
		this.explosiones = new ArrayList<>();
		this.bandera = true;
		this.gameOver = false;
	
		Thread hilo = new Thread(this);
		hilo.start();
	}
	
	public void iniciarJuego() {
		this.gameOver = false;
		this.contFase = 0;
		this.velocidad = 300;
		this.resetScore();
		this.jugador.setVidas();
		this.crearAliens();
		this.crearBarreras();
		this.nave = new Nave(225, 600, this);
		this.fase = 1;
		repaint();
	}
	
	private void dibujarScore(Graphics g) {
		if(this.fase!=3) {
			this.jugador.pintarScore(g);
		}
	}
	
	private void resetScore() {
		this.jugador.resetScore();
	}
	
	private void dibujarNave(Graphics2D g2) {
		try {
			this.nave.pintarNave(g2);	
		} catch(NullPointerException ex) {
		}
	}
	
	public void moverNaveIzq() {
		try {
			if(this.nave.getX() > 20) {
				this.nave.sumarX(-5);
			}
		} catch(NullPointerException ex) {
		}
	}	
	
	public void moverNaveDer() {
		try {
			if(this.nave.getX() < 470) {
				this.nave.sumarX(5);
			}
		} catch(NullPointerException ex) {
		}
	}
	
	private void crearAliens() {
		int alienX = 60;
		int alienY = 100;
		
		for(int i = 0; i < 55; i++) {
			this.aliens.add(new Alien(alienX, alienY, this));
			
			alienX += 40;
			if((i+1)%11 == 0) {
				alienX = 60;
				alienY += 50;
			}
		}
	}
	
	private void dibujarAliens(Graphics2D g2) {
		for(int i = 0; i < this.aliens.size(); i++) {
			this.aliens.get(i).pintarAlien(g2);
		}
	}
	
	private void moverAliens() {
		for(int i = 0; i < this.aliens.size(); i++) {
			this.aliens.get(i).moverAlien();
		}
	}
	
	public void crearBarreras() {
		int barreraX = 55;
		int barreraY = 500;
		int xInicio = 55;
		
		for(int i = 0; i < 64; i++) {
			this.barreras.add(new Barrera(barreraX, barreraY));
			
			barreraX += 15;
			if((i+1)%4 == 0) {
				barreraX = xInicio;
				barreraY += 15;
			}
			
			if((i+1)%16 == 0) {
				barreraX += 125;
				xInicio += 125;
				barreraY = 500;
			}
		}
	}
	
	private void eliminarBarreras() {
		for(int i = 0; i < this.barreras.size(); i++) {
			this.barreras.clear();
		}
	}	
	
	private void dibujarBarrera(Graphics2D g2) {
		for(int i = 0; i < this.barreras.size(); i++) {
			this.barreras.get(i).pintarBarrera(g2);
		}
	}
	
	public void crearDisparoNave() {
		try {	
			this.disparos.add(new Disparo(this.nave.getX(), this.nave.getY(), 1)); 		
		} catch(NullPointerException ex) {	
		}
	}
	
	public void crearDisparoAlien() {
		Random ran = new Random();
		int numero;
		
		for(int i = 0; i < this.aliens.size(); i++) {
			numero = ran.nextInt(30); //Escoje un numero random del 0 al 29 y el alien dispara si su numero es 0
			if(numero == 0) {
				this.disparos.add(new Disparo(this.aliens.get(i).getX()+12, this.aliens.get(i).getY()+18, 0));
			}
		}
	}
	
	private void dibujarDisparos(Graphics2D g2) {
		try {		
			for(int i = 0; i < this.disparos.size(); i++) {
				this.disparos.get(i).pintarDisparo(g2);
			}
		}catch(NullPointerException evt) {
			System.out.println("Error");
		}
			
	}
	
	private void moverDisparos() {
		for(int i = 0; i < this.disparos.size(); i++) {
			this.disparos.get(i).moverDisparo();
		}
	}
	
	private void pintarVidas(Graphics g) {
		int xVidas = 30,
			yVidas = 700;
		
		for(int i = 0; i < this.jugador.getVidas(); i++) {
			g.drawImage(new ImageIcon("assets/Nave.png").getImage(), xVidas, yVidas, 60, 20, this);
			xVidas += 80;
		}
	}
	
	private void disparoAcertado() {
		try {
			disparo: for(int i = 0; i < this.disparos.size(); i++) {
				for(int i2 = 0; i2 < this.aliens.size(); i2++) {
					
					if(this.disparos.get(i).colision(this.aliens.get(i2).getAlien()) && this.disparos.get(i).getTipo() == 1) { //Si el disparo i2 colisiona con el alien i Y el disparo proviene de la nave
						this.explosiones.add(new Explosion(this.aliens.get(i2).getX(), this.aliens.get(i2).getY(), this));
						this.disparos.remove(i);
						this.aliens.remove(i2);
						this.jugador.incrementScore();
						continue disparo;
				} 
					if(this.disparos.get(i).colision(this.nave.getNave()) && this.disparos.get(i).getTipo() == 0) { //Si el disparo i2 colisiona con la nave Y el disparo proviene de un alien
						this.disparos.remove(i);
						perderVida();
						continue disparo;
					}
				}
			}
		} catch (IndexOutOfBoundsException ex){
		} catch (NullPointerException ex) {
		}
	}
	
	private void disparoBarrera() {
		try {
			disparo: for(int i = 0; i < this.disparos.size(); i++) {
				for(int i2 = 0; i2 < this.barreras.size(); i2++) {
					if(this.disparos.get(i).colision(this.barreras.get(i2).getBarrera())) { 
						this.disparos.remove(i);
						this.barreras.remove(i2);
						continue disparo;
					} 
				}
			}
		} catch(IndexOutOfBoundsException ex) {
			System.out.println("Error bala 2");
		}
	}
	
	private void colisionAliensBarrera() {
		try {
			quitar: for(int i = 0; i < this.aliens.size(); i++) {
				for(int i2 = 0; i2 < this.barreras.size(); i2++) {
					if(this.aliens.get(i).getAlien().intersects(this.barreras.get(i2).getBarrera())) { 
						this.aliens.remove(i);
						this.barreras.remove(i2);
						continue quitar;
					} 
				}
			}
		} catch(IndexOutOfBoundsException ex) {
			System.out.println("Error colision");
		}
	}
	
	private void dibujarExplosiones(Graphics g) {
		for(int i = 0; i < this.explosiones.size(); i++) {
			this.explosiones.get(i).pintarExplosion(g);
		}
	}
	
	private void quitarExplosiones() {
		quitar: for(int i = 0; i < this.explosiones.size(); i++) {
			if(this.explosiones.get(i).quitarExplosion()) {
				this.explosiones.remove(i);
				continue quitar;
			}
		}
	}
	
	private void eliminarDisparo() {
		for(int i = 0; i < this.disparos.size(); i++) {
			if(this.disparos.get(i).getY() < -20 || this.disparos.get(i).getY() > 750) {
				this.disparos.remove(i);
			}
		}
	}
	
	private void siguienteNivel() {
		try {
			if(this.velocidad > 0) { 
				this.velocidad -= 50; //Hace que el movimiento de los aliens sea mas rapido
			}
			this.disparos.clear();
			eliminarBarreras();
			crearAliens();
			crearBarreras();
			Thread.sleep(2000);
		} catch (InterruptedException ex) {
		}
	}
	
	private void perderVida() {
		this.jugador.quitarVida();
		this.nave = null;
		if(this.jugador.getVidas() == 0) { //Si ya no quedan vidas, manda al game over, si todavia quedan vidas, manda a la pausa
			this.fase = 3; //GameOver
		} else {
			this.fase = 2; //Pausa
		}
	}
	
	private void gameOver(Graphics g) { //Pantalla de GameOver
		g.setColor(Color.WHITE);
		Font font = new Font("GAME OVER", Font.ITALIC, 50);
		g.setFont(font);
		g.drawString(font.getName(), 115, 200);
		g.drawString(Integer.toString(this.jugador.getScore()), 230, 350);
		g.drawString("PRESS ENTER TO", 65, 450);
		g.drawString("PLAY AGAIN", 135, 500);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.dibujarScore(g);
		Graphics2D g2 = (Graphics2D)g;
		this.dibujarAliens(g2);
		this.dibujarNave(g2);
		this.dibujarDisparos(g2);
		this.dibujarBarrera(g2);
		this.pintarVidas(g);
		this.dibujarExplosiones(g);
		if(this.gameOver) {
			gameOver(g);
		}
	}
	
	public void run() {
		while(this.bandera) {
			if(this.fase == 1) { //Juego
				if(this.contAliens++ == this.velocidad){
					this.contAliens = 0;
					moverAliens();
					crearDisparoAlien();
				}
				
				if(this.aliens.size() == 0) {
					siguienteNivel();
				}
				
				quitarExplosiones();
				moverDisparos();
				disparoAcertado();
				eliminarDisparo();
				disparoBarrera();
				colisionAliensBarrera();
			}
			
			if(this.fase == 2) { //Pausa
				quitarExplosiones();
				if(this.contFase++ == 400) {
					this.contFase = 0;
					this.disparos.clear();
					this.nave = new Nave(225, 600, this);
					this.fase = 1;
				}
			}
			
			if(this.fase == 3) { //GameOver
				quitarExplosiones();
				if(this.contFase++ == 500) {
					this.aliens.clear();
					this.disparos.clear();
					this.barreras.clear();
					this.gameOver = true;
				}
			}
			
			this.repaint();
			
			try {
				Thread.sleep(3);
			} catch (InterruptedException ex) {
				System.out.println("Error RUN");
			}

		}
	}
}

