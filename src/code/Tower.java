package code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tower extends GameObject {
	
	Handler handler;
	BufferedImage image;
	BufferedImage image1;
	BufferedImageLoader loader;



	public Tower(int x, int y,ID id,Handler handler) {
		super(x,y,id);
		this.handler = handler;
		this.setHealth(10000);
		velX = 0;
		
		loader = new BufferedImageLoader();
		image = loader.loadImage("res/Player_Tower.png");
		image1 = loader.loadImage("res/Enemy_Tower.png");
	}
	
	public void tick(){

	}
	
	public void render(Graphics g){
		if(this.getId() == ID.PlayerTower){
			g.drawImage(image, (int)x, (int)y, null);		
		} else if(this.getId() == ID.EnemyTower){
			g.drawImage(image1, (int)x, (int)y, null);
		}
		g.drawString("HP: " + this.getHealth(), this.getX(), (this.getY()-20));
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,150,400);
	}
}
