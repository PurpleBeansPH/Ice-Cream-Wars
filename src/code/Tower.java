package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Tower extends GameObject {
	
	Handler handler;

	public Tower(int x, int y,ID id,Handler handler) {
		super(x,y,id);
		this.handler = handler;
		this.setHealth(10000);
		velX = 0;
	}
	
	public void tick(){
		//Delete Object When Zero Health Screen
		//if(health <= 0){
		//	handler.removeObject(this);
		//}
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 100, 400);
		g.drawString("HP: " + this.getHealth(), this.getX(), (this.getY()-20));
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,100,400);
	}
}
