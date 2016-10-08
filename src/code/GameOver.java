package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.Game.STATE;

public class GameOver extends MouseAdapter {
	private Game game;

	public GameOver(Game game) {
		this.game = game;
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (game.gameState == STATE.GameOver) {
			if (mouseOver(mx, my, 380, 250, 500, 150)) {
				System.exit(0);
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

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

	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 80);
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawRect(380, 250, 500, 150);
		g.drawString("GAMEOVER", 400, 350);
	}
}
