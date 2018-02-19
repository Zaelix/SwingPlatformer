import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Player extends GameObject{
	
	
	public boolean left = false;
	public boolean right = false;
	
	private int xVelocity = 5;
	
	private int gravity = 1;
	private int yVelocity = 0;
	private int jumpPower = 20;
	
	private int yLimit = 500;

	boolean canJump = false;
	
	public Player(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void jump(){
		if(canJump){
			yVelocity -= jumpPower;
			canJump = false;
		}
	}
	
	public void update(){
		if(left){
			x -= xVelocity;
		}
		if(right){
			x += xVelocity;
		}
		
		yVelocity += gravity;
		y += yVelocity;
		
		if(y >= yLimit + 1){
			y = yLimit + 1;
			yVelocity = 0;
			canJump = true;
		}
		
		cBox.setBounds(x, y, this.getWidth(), this.getHeight());
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
	}
	
	public void setYLimit(int l){
		yLimit = l;
	}
	
	public int getYVelocity(){
		return yVelocity;
	}
}