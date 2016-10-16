package code;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sound {
	private File yourFile; // File Type WAV
	private AudioInputStream stream; // this is used to steam file
	private AudioFormat format; // Get Format
	private DataLine.Info info; // Info like how long and ect
	private Clip clip; // Chip or sound it self

	public Sound(String wav) {
		this.yourFile = new File(wav);
		try {
			this.stream = AudioSystem.getAudioInputStream(this.yourFile);
			this.format = this.stream.getFormat();
			this.info = new DataLine.Info(Clip.class, this.format);
			this.clip = (Clip) AudioSystem.getLine(this.info);
			this.clip.open(this.stream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play(){
		this.clip.start();
	}
	
	public void continuous(){
		this.clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void loop(int a){
		this.clip.loop(a);
	}
	
	public void stop() {
		this.clip.stop();
	}
}
