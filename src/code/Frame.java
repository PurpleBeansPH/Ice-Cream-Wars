package code;

import java.awt.image.BufferedImage;

public class Frame {
	private BufferedImage frame; // Variable that store image.

	public Frame(BufferedImage frame) {
		this.frame = frame; // Get Image and set it
	}

	public BufferedImage getFrame() {
		return frame; // Return Image.
	}

	public void setFrame(BufferedImage frame) {
		this.frame = frame; // Set Frame.
	}
}
