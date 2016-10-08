package code;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1280, HEIGHT = 720;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private AI ai;
	private Player player;
	private Display display;
	private Menu menu;
	private GameOver gameOver;
	private Sound sound;
	private BufferedImage graphic;

	public enum STATE {
		Menu, Game, GameOver;
	}

	public STATE gameState = STATE.Menu;

	public Game() {
		handler = new Handler();
		new Window(WIDTH, HEIGHT, "ICE CREAM WARS", this);
		display = new Display();
		player = new Player(handler, display);
		this.addKeyListener(player);
		ai = new AI(handler, display);
		menu = new Menu(this, handler);
		this.addMouseListener(menu);
		gameOver = new GameOver(this);
		this.addMouseListener(gameOver);
		// sound = new Sound("res/weeds.wav");
		sound = new Sound("res/8bit_Loop_BackFromPurgatory.wav");
		sound.continuous();
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
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
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

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