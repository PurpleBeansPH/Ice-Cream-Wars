package code;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Arrow extends GameObject {
	BufferedImage image;
	BufferedImageLoader loader;
	Random r = new Random();
	Handler handler;
	Sound sound;
	int randomNumber = 0;
	int[] maxDmg = { 75, 75, 100, 150, 250 };
	int[] minDmg = { 50, 50, 75, 125, 200 };

	public Arrow(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.setHealth(100);
		loader = new BufferedImageLoader();
		if (this.getId() == ID.PlayerArcherArrow) {
			image = loader.loadImage("res/Arrow.png");
			velX = 3;
		} else {
			image = loader.loadImage("res/EArrow.png");
			velX = -3;
		}
		sound = new Sound("res/CHEST_PUNCH.wav");
	}

	public void tick() {
		x += velX;
		y += velY;
		// Delete Object When Out Screen
		if (x < 0 || x > Game.WIDTH) {
			handler.removeObject(this);
		}
		// Delete Object When Zero Health Screen
		if (health <= 0) {
			handler.removeObject(this);
		}
		collision();
	}

	public void render(Graphics g) {
		g.drawImage(image, x, y, null);
		// g.drawRect(x, y, 32, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 16);
	}

	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (this.getId() == ID.PlayerArcherArrow) {
				if (tempObject.getId() == ID.EnemyFootSolder) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.EnemyArcher) {				
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.EnemyMage) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.EnemyBerserker) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.EnemyDragonSlayer) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.EnemyTower) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				}
			} else if (this.getId() == ID.EnemyArcherArrow) {
				if (tempObject.getId() == ID.PlayerFootSolder) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.PlayerArcher) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.PlayerMage) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.PlayerBerserker) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.PlayerDragonSlayer) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				} else if (tempObject.getId() == ID.PlayerTower) {
					if (getBounds().intersects(tempObject.getBounds())) {
						sound.loop(0);
						randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
						tempObject.setHealth(tempObject.getHealth() - randomNumber);
						this.setHealth(0);
					}
				}
			}
		}
	}
}
