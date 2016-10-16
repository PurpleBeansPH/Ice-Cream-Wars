package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

// See Archer if you want info
public class DragonSlayer extends GameObject {
	private Random r = new Random();
	private Handler handler;
	private SpriteSheet spriteSheet;
	private Sound sound;
	private int randomNumber = 0;
	private int[] maxDmg = { 75, 75, 100, 150, 250 };
	private int[] minDmg = { 50, 50, 75, 125, 200 };
	private int timeKeep = 0;
	// Images for each animation
	private BufferedImage[] walkImg = new BufferedImage[4];
	private BufferedImage[] attackImg = new BufferedImage[7];
	// These are animation states
	private Animation walk;
	private Animation attack;
	// This is the actual animation
	private Animation animation;

	public DragonSlayer(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.setHealth(2300);
		spriteSheet = new SpriteSheet(64);
		if (id == ID.PlayerDragonSlayer) {
			spriteSheet.loadSprite("res/Dragonslayer_Walk.png");
			for (int i = 0; i < walkImg.length; i++) {
				walkImg[i] = spriteSheet.grabImage(walkImg.length - i - 1, 0);
			}
			spriteSheet.loadSprite("res/Dragonslayer_Attack.png");
			for (int i = 0; i < attackImg.length; i++) {
				attackImg[i] = spriteSheet.grabImage(attackImg.length - i - 1, 0);
			}
			velX = 1;
		} else {
			spriteSheet.loadSprite("res/EDragonslayer_Walk.png");
			for (int i = 0; i < walkImg.length; i++) {
				walkImg[i] = spriteSheet.grabImage(i, 0);
			}
			spriteSheet.loadSprite("res/EDragonslayer_Attack.png");
			for (int i = 0; i < attackImg.length; i++) {
				attackImg[i] = spriteSheet.grabImage(i, 0);
			}
			velX = -1;
		}
		// After Loading Image Make Animation
		walk = new Animation(walkImg, 3);
		attack = new Animation(attackImg, 3);
		animation = walk;
		
		sound = new Sound("res/CHEST_PUNCH.wav");
	}

	public void tick() {
		x += velX;
		y += velY;
		timeKeep++;
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
		Font fnt = new Font("tahoma", 1, 10);
		if (this.getId() == ID.PlayerDragonSlayer) {
			g.drawImage(animation.getSprite(), x, y, null);
		} else if (this.getId() == ID.EnemyDragonSlayer) {
			g.drawImage(animation.getSprite(), x, y, null);
		}
		g.setFont(fnt);
		g.setColor(Color.DARK_GRAY);
		g.drawString("HP: " + this.getHealth(), this.getX(), (this.getY() - 20));
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 64);
	}

	/*
	 * This Method does all Collision Detecting and does Damage part
	 */
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (timeKeep >= 10) {
				animation = walk;
				if (this.getId() == ID.PlayerDragonSlayer) {
					this.setVelX(2);
					if (tempObject.getId() == ID.EnemyFootSolder) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[0] - this.minDmg[0]) + 1) + this.minDmg[0];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(-3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.EnemyArcher) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(-3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.EnemyMage) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) + 1) + this.minDmg[2];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(-3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.EnemyBerserker) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[3] - this.minDmg[3]) + 1) + this.minDmg[3];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(-3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.EnemyDragonSlayer) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(-3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.EnemyTower) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							this.setVelX(0);
							timeKeep = 0;
						}
					}
				} else if (this.getId() == ID.EnemyDragonSlayer) {
					this.setVelX(-2);
					if (tempObject.getId() == ID.PlayerFootSolder) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[0] - this.minDmg[0]) + 1) + this.minDmg[0];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.PlayerArcher) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.PlayerMage) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[2] - this.minDmg[2]) + 1) + this.minDmg[2];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.PlayerBerserker) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[3] - this.minDmg[3]) + 1) + this.minDmg[3];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.PlayerDragonSlayer) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							this.setHealth(this.getHealth() - randomNumber);
							this.setVelX(3);
							timeKeep = 0;
						}
					} else if (tempObject.getId() == ID.PlayerTower) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[4] - this.minDmg[4]) + 1) + this.minDmg[4];
							tempObject.setHealth(tempObject.getHealth() - randomNumber);
							this.setVelX(0);
							timeKeep = 0;
						}
					}
				}
			}
		}
	}
}
