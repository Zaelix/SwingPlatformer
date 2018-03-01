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
	
	Player p1 = new Player(this, 50, 50, 100, 100);
	
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
		timer = new Timer(10, this);

		platforms.add(new Platform(this, 0, 400, 200, 50));
		
		for (int i = 0; i < 4; i++) {
			int x = gen.nextInt(WIDTH);
			int y = gen.nextInt(HEIGHT);
			platforms.add(new Platform(this, x, y, 200, 50));
		}
		
		timer.start();
		
	}
	
	public void paintComponent(Graphics g){
		g.clearRect(0, 0, WIDTH, HEIGHT);
		p1.draw(g);
		
		for(Platform p : platforms){
			p.draw(g);
		}
	}
	
	public void actionPerformed(ActionEvent e){
		
		p1.update();
		
		for(Platform p : platforms){
			p.update();
		}
		
		repaint();
		
	}
	
	public boolean checkForCollisions(Player player){
		Rectangle nf = player.getCBox();
		for(Platform p: platforms){
			if(nf.intersects(p.getCBox())){
				player.rectifyCollision(getClosestClearSpace(nf, p.getCBox()));
				return true;
			}
		}
		
 		return false;
	}
	
	public Rectangle getClosestClearSpace(Rectangle p, Rectangle o) {
		Rectangle answer = new Rectangle();
		int dx = p.x - o.x;
		int dy = p.y - o.y;
		int changeX = 0;
		int changeY = 0;
		
		// Getting the needed change in Y
		if(p.y < o.y) {
			changeY = dy + p.height;
		}
		else {
			changeY = dy + o.height;
		}
		
		// Getting the needed change in X
		if(p.x > o.x) {
			changeX = dx - p.width;
		}
		else {
			changeX = dx - o.width;
		}
		
		if (changeX < changeY) {
			answer.x = p.x - changeX;
			answer.y = p.y;
		}
		else {
			answer.x = p.x;
			answer.y = p.y + changeY;
		}
		answer.width = p.width;
		answer.height = p.height;
		
		return answer;
	}
	
	
	

	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			p1.setXVelocity(-5);
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			p1.setXVelocity(5);
		}

		if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP){
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
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT){
			p1.setXVelocity(0);
		}
		if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_UP){
			p1.setIsJumping(false);
		}
		
	}
}
