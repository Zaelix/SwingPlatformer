import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

class Platform extends GameObject{
	
	public Platform(int x, int y, int w, int h){
		super(x, y, w, h);
	}
	
	public void update(){
		cBox.setBounds(x, y, this.getWidth(), this.getHeight());
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
	}
	
	public Rectangle getCBox(){
		return cBox;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}