import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Player extends GameObject{
	
	
	public boolean left = false;
	public boolean right = false;
	
	private int xVelocity = 0;
	
	private int gravity = 1;
	private int yVelocity = 0;
	private int jumpPower = 20;
	
	private int yLimit = 500;

	boolean isOnGround = false;
	
	public Player(GameHandler gh, int x, int y, int w, int h){
		super(gh, x, y, w, h);
	}
	
	public void jump(){
		if(isOnGround){
			yVelocity -= jumpPower;
			isOnGround = false;
		}
	}
	
	public void update(){
		this.move();
		
		if(y >= yLimit + 1){
			y = yLimit + 1;
			yVelocity = 0;
			isOnGround = true;
		}
		
		collider.setBounds(x, y, this.getWidth(), this.getHeight());
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
	}
	
	public void move() {
		
		yVelocity += gravity;
		Rectangle nextFrame = new Rectangle(x+xVelocity, y+yVelocity, this.getWidth(), this.getHeight());
		if(gh.checkForCollisions(nextFrame)) {
			stopMoving();
		}
		x += xVelocity;
		y += yVelocity;
		
	}
	
	public void setXVelocity(int v) {
		this.xVelocity = v;
	}
	
	public void stopMoving() {
		this.xVelocity = 0;
		this.yVelocity = 0;
	}
	
	public void setYLimit(int l){
		yLimit = l;
	}
	
	public int getYVelocity(){
		return yVelocity;
	}
}