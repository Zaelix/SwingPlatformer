//HERE ARE SOME CHANGES!!!

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GameHandler extends JPanel implements ActionListener, KeyListener{
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	
	JFrame window;
	Timer timer;
	Random gen = new Random();
	
	Player p1 = new Player(50, 50, 100, 100);
	
	ArrayList<Platform> platforms = new ArrayList<Platform>();
	
	public static void main(String[] args) {
		new GameHandler().run();
	}
	
	public void run(){
		window = new JFrame("JUMPER!!");
		window.addKeyListener(this);
		window.add(this);
		window.getContentPane().setPreferredSize(new Dimension(WIDTH, HEIGHT));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		window.pack();
		timer = new Timer(1000 / 60, this);
		
		for (int i = 0; i < 4; i++) {
			int x = gen.nextInt(WIDTH);
			int y = gen.nextInt(HEIGHT);
			platforms.add(new Platform(x, y, 200, 50));
		}
		
		timer.start();
		
	}
	
	public void paintComponent(Graphics g){
		p1.draw(g);
		
		for(Platform p : platforms){
			p.draw(g);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		checkCollision();
		
		p1.update();
		
		for(Platform p : platforms){
			p.update();
		}
		
		repaint();
		
	}
	
	private boolean checkCollides(Player plyr, Platform plat) {
		return false;
	}
	
	private boolean checkCollision(){
		for(Platform p: platforms){
			if(p1.getCBox().intersects(p.getCBox())){
				handleCollision(p);
				return true;
			}
		}
		
		p1.setYLimit(500);
		return false;
	}
	
	private void handleCollision(Platform p){
		if(p1.getYVelocity() >= 0 && p1.getY() + p1.getHeight() < p.getY() + 25){
			p1.setYLimit(p.getY() - p1.getHeight());
		}else{
			p1.setYLimit(500);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			p1.left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			p1.right = true;
		}

		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			p1.jump();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			timer.stop();
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			p1.left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			p1.right = false;
		}
	}
}
