package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Display {
	private BufferedImageLoader loader;
	private int timeReal = 0;
	private int time = 0;
	private int level = 1; // Level is used for gold
	private int levelCount = 1;
	private int levelAllowed = 1;
	private int goldCount = 1; // How many time did Player get gold.
	private int goldAllowed = 1; // This variable make Player only able get money every 5 seconds.
	private int gold = 600; // Starting Gold of Player
	private int cooldownOn = 0;
	// images
	private BufferedImage UI;
	private BufferedImage CooldownOn;
	private BufferedImage CooldownOff;

	public Display() {
		loader = new BufferedImageLoader(); // Make loader object to load images
		UI = loader.loadImage("res/UI.png"); // Load UI File 
		CooldownOff = loader.loadImage("res/Portrait.png"); // Load CooldownOff File
		CooldownOn = loader.loadImage("res/Portrait_Cooldown.png"); // Load CooldownOn File
	}

	/*
	 * This Tick Method get update by game loop in game class
	 * we use this bit for game logic like gold, time, and levels.
	 * Gold increase every 5 second or every 5 time and amount is base on gold = gold + (300*level)
	 * Level increase every 15 second or every 15 time
	 * time is based on timeReal/100
	 * timeReal increase every millisecond from Game loop
	 */
	public void tick() {
		timeReal++;
		time = timeReal / 100;
		if (time == 15 * levelCount) {
			if (levelAllowed == 1) {
				level++;
				levelCount++;
				levelAllowed = 0;
			}
		} else {
			levelAllowed = 1;
		}
		if (time == 5 * goldCount) {
			if (goldAllowed == 1) {
				gold = gold + (300 * level);
				goldCount++;
				goldAllowed = 0;
			}
		} else {
			goldAllowed = 1;
		}
	}
	
	/*
	 * this get update from game loop.
	 * we used this to display Gold and cooldown bar.
	 */
	public void render(Graphics g) {
		Font fnt = new Font("tahoma", 1, 30);
		g.drawImage(UI, 0, 0, null);
		if (cooldownOn == 0) {
			g.drawImage(CooldownOff, 425, 593, null);
		} else {
			g.drawImage(CooldownOn, 425, 593, null);
		}
		g.setFont(fnt);
		g.setColor(Color.DARK_GRAY);
		g.drawString(" " + gold, 70, 46);
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getCooldownOn() {
		return cooldownOn;
	}

	public void setCooldownOn(int cooldownOn) {
		this.cooldownOn = cooldownOn;
	}
}
