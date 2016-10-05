package code;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 1L;

    public static final int WIDTH = 1280, HEIGHT = 720;

    private Thread thread;
    private boolean running = false;
    
    private Handler handler;
    private AI ai;
    private Display display;

    public Game() {
    	handler = new Handler();
    	
        new Window(WIDTH, HEIGHT, "ICE CREAM WARS", this);
         
        display = new Display();
        this.addKeyListener(new Player(handler,display));
        ai = new AI(handler,display);
        
        handler.addObject(new Tower(100,220,ID.PlayerTower));
        handler.addObject(new Tower(1100,220,ID.EnemyTower));
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
    	display.tick();
    	ai.tick();
    	
    }
    
    public void render() {
    	BufferStrategy bs = this.getBufferStrategy();
    	if(bs == null){
    		this.createBufferStrategy(3);
    		return;
    	}
    	
    	Graphics g = bs.getDrawGraphics();
    	
    	g.setColor(Color.black);
    	g.fillRect(0, 0, WIDTH, HEIGHT);
    	
    	handler.render(g);
    	display.render(g);
    	
    	g.dispose();
    	bs.show();
    }

    public static void main(String[] args) {
        new Game();
    }

}