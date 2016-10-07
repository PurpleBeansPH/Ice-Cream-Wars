package code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import code.Game.STATE;

public class Menu extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu){
			if(mouseOver(mx,my,500, 100, 300, 100)){
				game.gameState = STATE.Game;
				handler.addObject(new Tower(100,220,ID.PlayerTower, handler));
        		handler.addObject(new Tower(1100,220,ID.EnemyTower, handler));
			}
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x , int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		Font fnt = new Font("arial",1, 50);
		Font fnt2 = new Font("arial",1, 25);
		
		g.setFont(fnt);
		g.setColor(Color.BLACK);
		g.drawString("Menu", 585, 60);
		
		g.setFont(fnt2);
		g.drawRect(500, 100, 300, 100);
		g.drawString("Play", 625, 150);

		g.drawRect(500, 300, 300, 100);
		g.drawString("Help", 625, 350);

		g.drawRect(500, 500, 300, 100);
		g.drawString("Quit", 625, 550);
		
	}
}
