package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Display {
	BufferedImageLoader loader;
	private int timeReal = 0;
	private int time = 0;
	private int level = 1;
	private int levelCount = 1;
	private int levelAllowed = 1;
	private int goldCount = 1;
	private int goldAllowed = 1;
	private int gold = 600;
	private int cooldownOn = 0;
	// images
	private BufferedImage UI;
	private BufferedImage CooldownOn;
	private BufferedImage CooldownOff;

	public Display() {
		loader = new BufferedImageLoader();
		UI = loader.loadImage("res/UI.png");
		CooldownOff = loader.loadImage("res/Portrait.png");
		CooldownOn = loader.loadImage("res/Portrait_Cooldown.png");
	}

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

	public void render(Graphics g) {
		Font fnt = new Font("tahoma", 1, 30);
		//g.drawString("time: " + time, 15, 64);
		//g.drawString("level: " + level, 15, 80);
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
