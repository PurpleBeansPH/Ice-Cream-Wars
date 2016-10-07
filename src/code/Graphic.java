package code;

import java.awt.image.BufferedImage;

public class Graphic {
	private BufferedImage Sprite_Sheet;
	private BufferedImage img;
	
	public Graphic(BufferedImage ss){
		this.Sprite_Sheet = ss;
	}
	
	public void grabImage(int col, int row, int width, int height){
		img = Sprite_Sheet.getSubimage((row*76)-76,(col*87)-87,width,height);
	}
	
	public BufferedImage getImg(){
		return img;
	}
}
