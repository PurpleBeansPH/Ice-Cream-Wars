package code;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class FireBall extends GameObject {
	Random r = new Random();
	Handler handler;
	SpriteSheet spriteSheet;
	Sound sound;
	int randomNumber = 0;
	int[] maxDmg = { 75, 75, 100, 150, 250 };
	int[] minDmg = { 50, 50, 75, 125, 200 };
	private BufferedImage[] fire = new BufferedImage[2];
	private Animation firing;
	private Animation animation;

	public FireBall(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.setHealth(100);
		spriteSheet = new SpriteSheet(32);
		if (this.getId() == ID.PlayerMageFireball) {
			spriteSheet.loadSprite("res/FireBall.png");
			for (int i = 0; i < fire.length; i++) {
				fire[i] = spriteSheet.grabImage(fire.length - i - 1, 0);
			}
			velX = 3;
		} else {
			spriteSheet.loadSprite("res/EFireBall.png");
			for (int i = 0; i < fire.length; i++) {
				fire[i] = spriteSheet.grabImage(fire.length - i - 1, 0);
			}
			velX = -3;
		}
		firing = new Animation(fire, 2);
		animation = firing;
		
		sound = new Sound("res/CHEST_PUNCH.wav");
	}

	public void tick() {
		x += velX;
		y += velY;
		animation.tick();
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
		g.drawImage(animation.getSprite(), x, y, null);
		// g.drawRect(x, y, 32, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 16);
	}

	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (this.getId() == ID.PlayerMageFireball) {
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
			} else if (this.getId() == ID.EnemyMageFireball) {
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
