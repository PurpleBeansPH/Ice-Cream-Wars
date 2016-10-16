package code;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1280, HEIGHT = 720; // Display Size
	private Thread thread; // Thread
	private boolean running = false; // is the thread running?
	private Handler handler; // Handler Class
	private AI ai; // Ai Class
	private Player player; // Player Class
	private Display display; // Display Class
	private Menu menu; // Menu Class
	private GameOver gameOver; // GameOver Class
	private Sound sound; // Sound Class
	private BufferedImage graphic; // store Background image

	public enum STATE { // State of game
		Menu, Game, GameOver;
	}

	public STATE gameState = STATE.Menu; // What is State is on

	public Game() {
		// First Load 
		handler = new Handler();
		display = new Display();
		
		player = new Player(handler, display); // Make Player Object
		this.addKeyListener(player);
		ai = new AI(handler, display); // Make ai Object
		menu = new Menu(this, handler); // Make menu Object
		this.addMouseListener(menu);
		gameOver = new GameOver(this); // Make gameOver Object
		this.addMouseListener(gameOver);
		sound = new Sound("res/8bit_Loop_BackFromPurgatory.wav");  // Make sound Object
		sound.continuous();
		
		new Window(WIDTH, HEIGHT, "ICE CREAM WARS", this); // Make window(JFrame)
	}
	/*
	 * Thread start
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	/*
	 * Thread stop
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * Thread Run
	 */
	public void run() {
		/*
		 * This is best part
		 * It CALLED GAME LOOP make mine job lot easier
		 * it has 2 part:
		 * TICK or TICK - First you tick anything like time, movement, logic, attacks, animations 
		 * RENDER or TOCK - Second You Render anything like Background, GameObject, GUI, and many more thing.
		 * 
		 * rest of GAME LOOP is used to make close to 60 ticks per second or 60 frames per second
		 * because you don't want waste system resources 
		 * 
		 * If want more info here http://www.java-gaming.org/index.php?topic=24220.0
		 * it talks about bad GAME LOOPS and Good GAME LOOPS
		 */
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick(); // Calls Tick Part
				delta--;
			}
			if (running)
				render(); // Calls Render Part
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	/*
	 * This is Tick Part of Game Loop
	 * this is used tick all game object, change gold values, and others things, checking if click buttons, and more thing.
	 */
	public void tick() {
		handler.tick();
		if (handler.gameOver == 1) {
			gameState = STATE.GameOver;
		}
		if (gameState == STATE.Game) {
			display.tick();
			ai.tick();
			player.tick();
		} else if (gameState == STATE.Menu) {
			menu.tick();
		} else if (gameState == STATE.GameOver) {
			gameOver.tick();
		}
	}
	
	/*
	 * This is Render part of Game Loop
	 * This is used render anything like Game Object, Buttons, Display.
	 */
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		BufferedImageLoader loader = new BufferedImageLoader();
		graphic = loader.loadImage("res/Ice_Cream_Wars_Stage_Draft.png");
		g.drawImage(graphic, 0, 0, null);
		handler.render(g);
		if (gameState == STATE.Game) {
			display.render(g);
		} else if (gameState == STATE.Menu) {
			menu.render(g);
		} else if (gameState == STATE.GameOver) {
			gameOver.render(g);
		}
		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Game();
	}
}