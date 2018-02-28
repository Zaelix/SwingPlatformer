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

	boolean isJumping = false;
	
	public Player(GameHandler gh, int x, int y, int w, int h){
		super(gh, x, y, w, h);
	}
	
	public void jump(){
		if(isGrounded()) {
			yVelocity = -jumpPower;
		}
	}
	
	public void update(){
		this.move();
		
		collider.setBounds(x, y, this.getWidth(), this.getHeight());
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLUE);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
	}
	
	public void move() {
		
		x += xVelocity;
		if(!this.isGrounded()) {
			gravity++;
			y += yVelocity + gravity;
		}
		else {
			gravity = 1;
			y += yVelocity;
		}
		 if (!isJumping) {
			 yVelocity = 0;
		 }
		
	}
	
	public boolean isGrounded() {
		Rectangle nextFrame = new Rectangle(x, y+1, this.getWidth(), this.getHeight());
		if(gh.checkForCollisions(this.getCBox())) {
			return true;
		}
		//isOnGround = false;
		return false;
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
	public void setIsJumping(boolean b) {
		isJumping = b;
	}
}