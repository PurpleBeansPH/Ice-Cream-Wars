package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Archer extends GameObject {
	private Random r = new Random(); // Random Number Generator
	private Handler handler; // To Spawn Arrows
	private SpriteSheet spriteSheet; // To Load Images
	private Sound sound; // To play Hit Sounds
	int randomNumber = 0; // RandomNumber variable
	int[] maxDmg = { 75, 75, 100, 150, 250 }; // Self Explained
	int[] minDmg = { 50, 50, 75, 125, 200 }; // Self Explained
	private int timeKeep = 0; // It used for cooldown for attacks
	private int arrowTime = 0; // Make Time Variable for Arrow
	// Images for each animation
	private BufferedImage[] walkImg = new BufferedImage[4];
	private BufferedImage[] attackImg = new BufferedImage[5];
	// These are animation states
	private Animation walk;
	private Animation attack;
	// This is the actual animation
	private Animation animation;

	public Archer(int x, int y, ID id, Handler handler) {
		super(x, y, id); // Call GameObject to set X,Y, and ID
		this.handler = handler; // This Handler to able to spawn arrow
		this.setHealth(400); // Set Health
		spriteSheet = new SpriteSheet(64); // Make Object to Able to load Images.
		if (id == ID.PlayerArcher) { // If is Player Archer Do This
			spriteSheet.loadSprite("res/Archer_Walk.png"); // Load Player Archer Walk Image File
			for (int i = 0; i < walkImg.length; i++) { // For loop
				walkImg[i] = spriteSheet.grabImage(walkImg.length - i - 1, 0); // Load Image
			}
			spriteSheet.loadSprite("res/Archer_Attack.png"); // Load Player Archer Attack Image File
			for (int i = 0; i < attackImg.length; i++) { // For loop
				attackImg[i] = spriteSheet.grabImage(attackImg.length - i - 1, 0); // Load Image
			}
			velX = 1; // set Velocity of Player Archer to x = 1
		} else { // Else Enemy Archer
			spriteSheet.loadSprite("res/EArcher_Walk.png"); // Load Enemy Archer Walk Image File
			for (int i = 0; i < walkImg.length; i++) {
				walkImg[i] = spriteSheet.grabImage(i, 0); // Load Image
			}
			spriteSheet.loadSprite("res/EArcher_Attack.png"); // Load Enemy Archer Attack Image File
			for (int i = 0; i < attackImg.length; i++) {
				attackImg[i] = spriteSheet.grabImage(i, 0); // Load Image
			}
			velX = -1;
		}
		// After Loading Image Make Animation
		walk = new Animation(walkImg, 3); // Make Walk Animation
		attack = new Animation(attackImg, 5); // Make Attacking Animation
		animation = walk; // Run Walk Animation
		
		sound = new Sound("res/CHEST_PUNCH.wav"); // Load Sound
	}

	public void tick() {
		x += velX;
		y += velY;
		timeKeep++;
		arrowTime++;
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
		if (this.getId() == ID.PlayerArcher) {
			g.drawImage(animation.getSprite(), x, y, null);
		} else if (this.getId() == ID.EnemyArcher) {
			g.drawImage(animation.getSprite(), x, y, null);
		}
		g.setFont(fnt);
		g.setColor(Color.DARK_GRAY);
		g.drawString("HP: " + this.getHealth(), this.getX(), (this.getY() - 20));
	}

	public Rectangle getBounds() {
		if (this.getId() == ID.PlayerArcher)
			return new Rectangle(x + 10, y, 32, 64);
		else
			return new Rectangle(x + 20, y, 32, 64);
	}
	
	/*
	 * This Method does all Collision Detecting and does Damage part also for this class also Arrow
	 */
	public void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (timeKeep >= 10) {
				if (arrowTime > 5) {
					animation = walk;
					if (this.getId() == ID.PlayerArcher) {
						this.setVelX(1);
					} else {
						this.setVelX(-1);
					}
				}
				if (arrowTime > 80) {
					this.setVelX(0);
					animation = attack;
					if (arrowTime > 100) {
						if (this.getId() == ID.PlayerArcher) {
							handler.addObject(new Arrow(this.getX() + 16, this.getY() + 32, ID.PlayerArcherArrow, handler));
							this.setVelX(0);
						} else {
							handler.addObject(new Arrow(this.getX() - 16, this.getY() + 32, ID.EnemyArcherArrow, handler));
							this.setVelX(0);
						}
						this.arrowTime = 0;
					}
				}
				if (this.getId() == ID.PlayerArcher) {
					if (tempObject.getId() == ID.EnemyFootSolder) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
				} else if (this.getId() == ID.EnemyArcher) {
					if (tempObject.getId() == ID.PlayerFootSolder) {
						if (getBounds().intersects(tempObject.getBounds())) {
							animation = attack;
							sound.loop(1);
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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
							randomNumber = r.nextInt((this.maxDmg[1] - this.minDmg[1]) + 1) + this.minDmg[1];
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