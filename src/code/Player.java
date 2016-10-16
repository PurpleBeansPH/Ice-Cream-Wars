package code;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener {
	private Handler handler; // Make Player class able to talk Handler to spawn enemy.
	private Display display; // Make Player class able check time for gold.
	int time = 0; // Time basicaly same as TimeKeep in AI Class
	int cooldownTime = 0; // CooldownTime Start.
	int tickSec = 0; // This used to update cooldownTime variable.

	public Player(Handler handler, Display display) {
		this.handler = handler; // Get Handler from Game class.
		this.display = display; // Get Display from Game class.
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode(); // Get KeyCode when you press key
		if (cooldownTime < time) { // Cooldown Time this prevent user spam enemy.
			if (key == KeyEvent.VK_1) { // Check if key you press is == 1
				if (display.getGold() >= 100) { // Check if have enough Gold
					handler.addObject(new FootSoldier(50, 490, ID.PlayerFootSolder, handler));
					display.setGold(display.getGold() - 100);
					cooldownTime = time;
				}
			} else if (key == KeyEvent.VK_2) { // Check if key you press is == 2
				if (display.getGold() >= 300) { // Check if have enough Gold
					handler.addObject(new Archer(50, 490, ID.PlayerArcher, handler));
					display.setGold(display.getGold() - 300);
					cooldownTime = time;
				}
			} else if (key == KeyEvent.VK_3) { // Check if key you press is == 3
				if (display.getGold() >= 600) { // Check if have enough Gold
					handler.addObject(new Mage(50, 490, ID.PlayerMage, handler));
					display.setGold(display.getGold() - 600);
					cooldownTime = time;
				}
			} else if (key == KeyEvent.VK_4) { // Check if key you press is == 4
				if (display.getGold() >= 1000) { // Check if have enough Gold
					handler.addObject(new Berserker(50, 490, ID.PlayerBerserker, handler));
					display.setGold(display.getGold() - 1000);
					cooldownTime = time;
				}
			} else if (key == KeyEvent.VK_5) { // Check if key you press is == 5
				if (display.getGold() >= 3000) { // Check if have enough Gold
					handler.addObject(new DragonSlayer(50, 490, ID.PlayerDragonSlayer, handler));
					display.setGold(display.getGold() - 3000);
					cooldownTime = time;
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	public void tick() {
		time = display.getTime(); // Get Time
		if (cooldownTime < time) // // Cooldown Time this prevent user spam enemy.
			display.setCooldownOn(0); // Set Display of Cooldown to off
		else
			display.setCooldownOn(1); // Set Display of Cooldown to on
	}
}
