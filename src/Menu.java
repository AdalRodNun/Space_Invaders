//Hecho por Joel Santillan - A01634748 y por Adalberto Rodriguez - A01114713
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Menu extends JPanel implements MouseListener{
	private static final int ANCHO = 550,
							 ALTO = 750;
	private Color color;
	//private Tablero tb;
	//private Contenedor ct;
	
	public Menu() {
		super();
		this.setPreferredSize(new Dimension(ANCHO, ALTO));
		this.addMouseListener(this);
		this.setColor(Color.WHITE);
		setBackground(Color.BLACK);
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void dibujaTexto(Graphics g){
		g.setColor(color);
		Font font1 = new Font("SPACE INVADERS",Font.ITALIC,50);
		g.setFont(font1);
		g.drawString(font1.getName(), 65, 100);
		Font font2 = new Font("PRESS ENTER TO PLAY",Font.ITALIC,35);
		g.setFont(font2);
		g.drawString(font2.getName(), 80, 500);
		
	}
	
	public void dibujaTitulo(Graphics g) {
		g.drawImage(new ImageIcon("assets/alien1.png").getImage(), 175, 125, this);	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.dibujaTexto(g);	
		this.dibujaTitulo(g);
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent evt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setColor(Color.GREEN);
		repaint();	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		this.setColor(Color.WHITE);
		//this.tb.iniciarJuego();
		//this.ct.panelTablero();
		repaint();
	}
}
