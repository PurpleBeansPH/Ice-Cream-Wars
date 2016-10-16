package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.Game.STATE;

public class Menu extends MouseAdapter {
	private Game game; // Connect to Game Class
	private Handler handler; // Connect to Handler Class

	public Menu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	/*
	 * If the mouse press it get X and Y and try to check if pressed any button
	 */
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == STATE.Menu) {
			if (mouseOver(mx, my, 500, 260, 300, 60)) { // If mouse is on Game Button Game STATE on
				game.gameState = STATE.Game;
				handler.addObject(new Tower(35, 151, ID.PlayerTower, handler));
				handler.addObject(new Tower(1100, 151, ID.EnemyTower, handler));
			} else if (mouseOver(mx, my, 500, 360, 300, 60)) { // If mouse is on quit then game quit.
				System.exit(0);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

	/*
	 * This Method Check if mouse is on button 
	 */
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void tick() {
	}

	// Renders Buttons and Text
	public void render(Graphics g) {
		Font fnt = new Font("tahoma", 1, 50);
		Font fnt2 = new Font("tahoma", 1, 25);
		g.setFont(fnt);
		g.setColor(Color.BLACK);
		g.drawString("ICE CREAM WARS", 425, 150);
		g.setFont(fnt2);
		g.drawRect(500, 260, 300, 60); // This is Play Button
		g.drawString("PLAY", 620, 300);
		//g.drawRect(500, 310, 300, 60);
		//g.drawString("HELP", 620, 350);
		g.drawRect(500, 360, 300, 60); // This is Quit Button
		g.drawString("QUIT", 620, 400);
	}
}
