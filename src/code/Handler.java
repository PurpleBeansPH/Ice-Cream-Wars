package code;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	int gameOver = 0;
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
			if((tempObject.getId() == ID.PlayerTower)||(tempObject.getId() == ID.EnemyTower)){
				if(tempObject.getHealth() <= 0){
					this.gameOver = 1;
					removeObject(tempObject);
				}
			}
			
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
			
		}
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
}
