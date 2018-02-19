import java.awt.Rectangle;

public class GameObject {
	public int x;
	public int y;
	private int width;
	private int height;
	
	public Rectangle cBox = new Rectangle();
	
	public GameObject(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
		this.width = w;
		this.height = h;
		
		this.cBox.setBounds(x, y, width, height);
	}
	
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Rectangle getCBox(){
		return cBox;
	}
}
