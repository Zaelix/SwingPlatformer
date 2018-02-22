import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Platform extends GameObject{
	
	public Platform(GameHandler gh, int x, int y, int w, int h){
		super(gh, x, y, w, h);
	}
	
	public void update(){
		collider.setBounds(x, y, this.getWidth(), this.getHeight());
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
	}
	
	public Rectangle getCBox(){
		return collider;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}