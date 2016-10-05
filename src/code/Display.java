package code;

import java.awt.Graphics;

public class Display {
	private int timeReal = 0;
	private int time = 0;
	private int level = 1;
	private int levelCount = 1;
	private int levelAllowed = 1;
	private int goldCount = 1;
	private int goldAllowed = 1;
	private int gold = 600;
	
	public void tick(){
		timeReal++;
		time = timeReal/100;
		if(time == 5 * goldCount) {
			if(goldAllowed == 1) {
				gold = gold + (300*level);
				goldCount++;
				goldAllowed = 0;
			}
		} else {
			goldAllowed = 1;
		}
		if(time == 20 * levelCount) {
			if(levelAllowed == 1) {
				level++;
				levelCount++;
				levelAllowed = 0;
			}
		} else {
			levelAllowed = 1;
		}
	}
	
	public void render(Graphics g){
		g.drawString("time: " + time, 15, 64);
		g.drawString("level: " + level, 15, 80);
		g.drawString("gold: " + gold, 15, 100);
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
}
