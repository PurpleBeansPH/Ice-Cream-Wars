package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
	
	private Handler handler;
	private Display display;
	
	int time = 0;
	int cooldownTime = 0;
	
	public Player(Handler handler, Display display){
		this.handler = handler;
		this.display = display;
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		time = display.getTime();
		if(cooldownTime < time){
			if(key == KeyEvent.VK_1) {
				if(display.getGold() > 100){
					handler.addObject(new FootSoldier(50,600,ID.PlayerFootSolder,handler));
					display.setGold(display.getGold() - 100);
					cooldownTime = time;
				}
			}
			if(key == KeyEvent.VK_2) {
				if(display.getGold() > 300){
					handler.addObject(new Archer(50,600,ID.PlayerArcher,handler));
					display.setGold(display.getGold() - 300);
					cooldownTime = time;
				}
			}
			if(key == KeyEvent.VK_3) {
				if(display.getGold() > 600){
					handler.addObject(new Mage(50,600,ID.PlayerMage,handler));
					display.setGold(display.getGold() - 600);
					cooldownTime = time;
				}
			}
			if(key == KeyEvent.VK_4) {
				if(display.getGold() > 1000){
					handler.addObject(new Berserker(50,600,ID.PlayerBerserker,handler));
					display.setGold(display.getGold() - 1000);
					cooldownTime = time;
				}
			}
			if(key == KeyEvent.VK_5) {
				if(display.getGold() > 3000){
					handler.addObject(new DragonSlayer(50,600,ID.PlayerDragonSlayer,handler));
					display.setGold(display.getGold() - 3000);
					cooldownTime = time;
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}

