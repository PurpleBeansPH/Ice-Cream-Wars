package code;

import java.awt.Graphics;
import java.awt.Rectangle;

/*
 *  This is Class is used for any game object from Units, Building, projectile and many more.
 */
public abstract class GameObject {
	protected int x, y; // X and Y to get position on window
	protected ID id; // To see if enemy object or player object
	protected int velX, velY; // Movement of Object
	protected int health; // Health of object

	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public ID getId() {
		return this.id;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int velX() {
		return this.velX;
	}

	public int velY() {
		return this.velY;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
