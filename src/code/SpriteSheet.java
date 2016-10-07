package code;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage Sprite_Sheet;
	private BufferedImage img;
	
	public SpriteSheet(BufferedImage ss){
		this.Sprite_Sheet = ss;
	}
	
	public void grabImage(int col, int row, int width, int height){
		img = Sprite_Sheet.getSubimage((row*64)-64,(col*64)-64,width,height);
	}
	
	public BufferedImage getImg(){
		return img;
	}
}