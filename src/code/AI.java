package code;

public class AI {
	private Handler handler; // Make AI class able to talk Handler to spawn enemy.
	private Display display; // Make AI class able check time for gold.
	private int aiGold = 600; // Starting Gold of AI.
	private int aiGoldCount = 1; // How many time did AI get gold.
	private int aiGoldAllowed = 1; // This variable make AI only able get money after 5 seconds.
	private int timeKeep = 0; // To Store Display Time
	private int cooldownTime = 0; // CooldownTime Start.

	public AI(Handler handler, Display display) {
		this.handler = handler; // Get Handler from Game class.
		this.display = display; // Get Display from Game class.
	}

	public void tick() {
		timeKeep = display.getTime(); // Get Time from display class.
		if (timeKeep == 5 * aiGoldCount) { // Check if 5 sec are done.
			if (aiGoldAllowed == 1) { // This prevent mill seconds able to respawn gold
				aiGold = aiGold + (300 * (display.getLevel()));
				aiGoldCount++;
				aiGoldAllowed = 0;
			}
		} else {
			aiGoldAllowed = 1;
		}
		if (cooldownTime < timeKeep) { // Cooldown Time this prevent user spam enemy.
			if (aiGold >= 3000) {
				handler.addObject(new DragonSlayer(1230, 490, ID.EnemyDragonSlayer, handler));
				aiGold = aiGold - 3000;
				cooldownTime = timeKeep;
			} else if (aiGold >= 1000) {
				handler.addObject(new Berserker(1230, 490, ID.EnemyBerserker, handler));
				aiGold = aiGold - 1000;
				cooldownTime = timeKeep;
			} else if (aiGold >= 600) {
				handler.addObject(new Mage(1230, 490, ID.EnemyMage, handler));
				aiGold = aiGold - 600;
				cooldownTime = timeKeep;
			} else if (aiGold >= 300) {
				handler.addObject(new Archer(1230, 490, ID.EnemyArcher, handler));
				aiGold = aiGold - 300;
				cooldownTime = timeKeep;
			} else if (aiGold >= 100) {
				handler.addObject(new FootSoldier(1230, 490, ID.EnemyFootSolder, handler));
				aiGold = aiGold - 100;
				cooldownTime = timeKeep;
			}
		}
	}

	public void render() {
	}
}
