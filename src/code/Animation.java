package code;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
	private int frameCount; // Counts ticks for change
	private int frameDelay; // frame delay 1-12 (You will have to play around
							// with this)
	private int currentFrame; // animations current frame
	private int totalFrames; // total amount of frames for your animation
	private boolean stopped; // has animations stopped
	private List<Frame> frames = new ArrayList<Frame>(); // Arraylist of frames

	public Animation(BufferedImage[] frames, int frameDelay) {
		this.frameDelay = frameDelay; // Set Delay
		this.stopped = false; // Is Stop NO.
		for (int i = 0; i < frames.length; i++) { // To Add Images to frames array
			addFrame(frames[i]);
		}
		this.frameCount = 0; // Frame Count
		this.currentFrame = 0; // Current Frame
		this.totalFrames = this.frames.size(); // Give TotalFrame the Total Frame Size.
	}

	private void addFrame(BufferedImage frame) { // Add Frame From Image.
		frames.add(new Frame(frame));
		currentFrame = 0;
	}

	public BufferedImage getSprite() { // Get Current Frame.
		return frames.get(currentFrame).getFrame();
	}

	public void tick() { // Frame Cycle.
		if (!stopped) {
			frameCount++;
			if (frameCount > frameDelay) {
				frameCount = 0;
				currentFrame++;
				if (currentFrame > totalFrames - 1) {
					currentFrame = 0;
				} else if (currentFrame < 0) {
					currentFrame = totalFrames - 1;
				}
			}
		}
	}
}
