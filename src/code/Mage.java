package code;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Mage extends GameObject {
	
	Random r = new Random();
	Handler handler;
	SpriteSheet SpriteSheet;
	SpriteSheet SpriteSheet2;
	BufferedImageLoader image;
	
	int randomNumber = 0;
	int[] maxDmg = {75,75,100,150,250};
	int[] minDmg = {50,50,75,125,200};
	
	public Mage (int x, int y,ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		this.setHealth(1000);
		
		if(id == ID.EnemyMage)
			velX = -2;
		else
			velX = 2;
		
		image = new BufferedImageLoader();
		SpriteSheet = new SpriteSheet(image.loadImage("res/Mage_Walk.png"));
		SpriteSheet.grabImage(1, 2, 64, 64);
		SpriteSheet2 = new SpriteSheet(image.loadImage("res/EMage_Walk.png"));
		SpriteSheet2.grabImage(1, 2, 64, 64);
	}
	
	public void tick(){
		x += velX;
		y += velY;
		
		//Delete Object When Out Screen
		if(x<0||x>Game.WIDTH){
			handler.removeObject(this);
		}
		
		//Delete Object When Zero Health Screen
		if(health <= 0){
			handler.removeObject(this);
		}
		
		collision();
	}
	
	public void render(Graphics g){
		if(this.getId() == ID.PlayerMage){
			g.drawImage(SpriteSheet.getImg(), (int)x, (int)y, null);		
		} else if(this.getId() == ID.EnemyMage){
			g.drawImage(SpriteSheet2.getImg(), (int)x, (int)y, null);
		}
		g.drawString("HP: " + this.getHealth(), this.getX(), (this.getY()-20));
	}

	public Rectangle getBounds() {
		return new Rectangle(x,y,32,64);
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(this.getId() == ID.PlayerMage) {
				if(tempObject.getId() == ID.EnemyFootSolder) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[0] - this.minDmg[0]) +1) + this.minDmg[0];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() - r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() + r.nextInt((150-100)+100));
						this.setVelX(2);
					}
				} else if(tempObject.getId() == ID.EnemyArcher) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) +1) + this.minDmg[1];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() - r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() + r.nextInt((150-100)+100));
						this.setVelX(2);
					}
				} else if(tempObject.getId() == ID.EnemyMage) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() - r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() + r.nextInt((150-100)+100));
						this.setVelX(2);
					}
				} else if(tempObject.getId() == ID.EnemyBerserker) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[3] - this.minDmg[3]) +1) + this.minDmg[3];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() - r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() + r.nextInt((150-100)+100));
						this.setVelX(2);
					}
				} else if(tempObject.getId() == ID.EnemyDragonSlayer) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) +1) + this.minDmg[4];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() - r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() + r.nextInt((150-100)+100));
						this.setVelX(2);
					}
				} else if(tempObject.getId() == ID.EnemyTower) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setVelX(0);
					}
				}
			} else if(this.getId() == ID.EnemyMage) {
				if(tempObject.getId() == ID.PlayerFootSolder) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[0] - this.minDmg[0]) +1) + this.minDmg[0];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() + r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() - r.nextInt((150-100)+100));
						this.setVelX(-2);
					}
				} else if(tempObject.getId() == ID.PlayerArcher) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) +1) + this.minDmg[1];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() + r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() - r.nextInt((150-100)+100));
						this.setVelX(-2);
					}
				} else if(tempObject.getId() == ID.PlayerMage) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() + r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() - r.nextInt((150-100)+100));
						this.setVelX(-2);
					}
				} else if(tempObject.getId() == ID.PlayerBerserker) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[3] - this.minDmg[3]) +1) + this.minDmg[3];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() + r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() - r.nextInt((150-100)+100));
						this.setVelX(-2);
					}
				} else if(tempObject.getId() == ID.PlayerDragonSlayer) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) +1) + this.minDmg[4];
						this.setHealth(this.getHealth() - randomNumber);
						this.setX(this.getX() + r.nextInt((150-100)+100));
						tempObject.setX(tempObject.getX() - r.nextInt((150-100)+100));
						this.setVelX(-2);
					}
				} else if(tempObject.getId() == ID.PlayerTower) {
					if(getBounds().intersects(tempObject.getBounds())) {
						randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) +1) + this.minDmg[2];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setVelX(0);
					}
				}
			}
		}
	}

}
