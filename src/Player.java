import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Player extends GameObject{
	
	
	public boolean left = false;
	public boolean right = false;
	
	private int xVelocity = 0;
	
	private int yVelocity = 0;
	private int jumpPower = -40;

	boolean isColliding = false;
	
	public Player(GameHandler gh, int x, int y, int w, int h){
		super(gh, x, y, w, h);
	}
	
	public void jump(){
		if(yVelocity <= 0) {
			yVelocity = jumpPower;
		}
		isColliding = true;
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
		y += yVelocity;
		isColliding = isGrounded();
		if(yVelocity < 15 && isColliding == false) {
			yVelocity++;
		}
		if (isColliding) {
			yVelocity = 0;
		}
	}
	
	public boolean isGrounded() {
		if(gh.checkForCollisions(this)) {
			//System.out.println("Colliding! " + yVelocity);
			return true;
		}
		return false;
	}
	
	public void rectifyCollision(Rectangle newLocRec) {
		this.x = newLocRec.x;
		this.y = newLocRec.y;
	}
	
	public void setXVelocity(int v) {
		this.xVelocity = v;
	}
	
	public int getYVelocity(){
		return yVelocity;
	}
	public void setIsJumping(boolean b) {
		isColliding = b;
	}
}