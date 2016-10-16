package code;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>(); // Make List For all GameObjects
	int gameOver = 0; // Game Over Variable

	/*
	 * This Will Tick Every Game Object So it can move, Attack, Sound, Collision 
	 */
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
			if ((tempObject.getId() == ID.PlayerTower) || (tempObject.getId() == ID.EnemyTower)) {
				if (tempObject.getHealth() <= 0) {
					this.gameOver = 1;
					removeObject(tempObject);
				}
			}
		}
	}
	
	/*
	 * This Will Render Every Game Object So You Can SEE THE OBJECT.
	 */
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}

	/*
	 * This Add GameObjets
	 */
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	/*
	 * This Add GameObjets
	 */
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
