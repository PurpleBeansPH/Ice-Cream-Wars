package code;

import java.util.Random;

public class AI {
	
	Random r = new Random();
	
	private Handler handler;
	private Display display;
	
	private int aiGold = 600;
	private int aiGoldCount = 1;
	private int aiGoldAllowed = 1;
	private int timeKeep = 0;
	private int cooldownTime = 0;
	private int randomNumber = 0;
	
	public AI(Handler handler, Display display){
		this.handler = handler;
		this.display = display;
	}
	
	public void tick(){
		timeKeep = display.getTime();
		if(timeKeep == 5 * aiGoldCount) {
			if(aiGoldAllowed == 1) {
				aiGold = aiGold + (300*display.getLevel());
				aiGoldCount++;
				aiGoldAllowed = 0;
			}
		} else {
			aiGoldAllowed = 1;
		}
		if(cooldownTime < timeKeep){
			if(aiGold > 3000){
				randomNumber = r.nextInt(100-50+1)+50;
				handler.addObject(new DragonSlayer(1100+randomNumber,600,ID.EnemyDragonSlayer,handler));
				aiGold = aiGold - 3000;
				cooldownTime = timeKeep;
			}
			else if(aiGold > 1000){
				randomNumber = r.nextInt(100-50+1)+50;
				handler.addObject(new Berserker(1100+randomNumber,600,ID.EnemyBerserker,handler));
				aiGold = aiGold - 1000;
				cooldownTime = timeKeep;
			}
			else if(aiGold > 600){
				randomNumber = r.nextInt(100-50+1)+50;
				handler.addObject(new Mage(1100+randomNumber,600,ID.EnemyMage,handler));
				aiGold = aiGold - 600;
				cooldownTime = timeKeep;
			}
			else if(aiGold > 300){
				randomNumber = r.nextInt(100-50+1)+50;
				handler.addObject(new Archer(1100+randomNumber,600,ID.EnemyArcher,handler));
				aiGold = aiGold - 300;
				cooldownTime = timeKeep;
			}
			else if(aiGold > 100){
				randomNumber = r.nextInt(100-50+1)+50;
				handler.addObject(new FootSoldier(1100+randomNumber,600,ID.EnemyFootSolder,handler));
				aiGold = aiGold - 100;
				cooldownTime = timeKeep;
			}
		}
	}
	
	public void render(){
		
	}
}
