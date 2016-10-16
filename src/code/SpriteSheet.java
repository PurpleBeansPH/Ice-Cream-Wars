package code;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private BufferedImage Sprite_Sheet; // Image
	private int tileSize = 64; // Sprite Size

	public SpriteSheet(int tileSize) {
		this.tileSize = tileSize;
	}

	public BufferedImage loadSprite(String path) { // Load SpriteSheet
		try {
			Sprite_Sheet = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Sprite_Sheet;
	}

	public BufferedImage grabImage(int col, int row) { // Get Sprite from SpriteSheet
		return Sprite_Sheet.getSubimage(col * tileSize, row * tileSize, tileSize, tileSize);
	}
}